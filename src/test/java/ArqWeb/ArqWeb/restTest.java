package ArqWeb.ArqWeb;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import Dao.TrabajoDAO;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class restTest {
	
	private final String BASE_URL="http://localhost:8080/ArqWeb/api";
	
	private final int OK = 201;

	private final HttpClient client = HttpClientBuilder.create().build();
	
	@BeforeClass
    public void testInit(){
		System.out.println("restTest");
    }
	
	@BeforeTest
	public void serverIsUp() throws ClientProtocolException, IOException {
		System.out.println("serverIsUp");
		String url = this.BASE_URL + "/palabraClave/1";
		HttpGet get = new HttpGet(url);
		HttpResponse response = this.client.execute(get);
		assertEquals(this.OK,response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void testPalabrasClaves() throws ClientProtocolException, IOException {
//		this.nuevasPalabrasClave("Ruby",false);
//		this.nuevasPalabrasClave("API",false);
//		this.nuevasPalabrasClave("VUE",true);
//		this.nuevasPalabrasClave("Angular",false);
		this.nuevosTrabajos("ERP", "Enterprise resource planning");
//		this.nuevosTrabajos("CRM", "Customer-relationship management");
//		this.nuevosTrabajos("MRP", "Material requirements planning");
	}
	
	//esto no va, no lo piden
	public void nuevasPalabrasClave(String palabra, Boolean esExperto ) throws ClientProtocolException, IOException {
		String url = this.BASE_URL + "/palabraClave";
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();
		jsonObject.put("palabra", palabra);
		jsonObject.put("es_experto", esExperto);
		String jsonString = jsonObject.toString();
		HttpPost post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		HttpResponse response = this.client.execute(post);
		assertEquals(response.getStatusLine().getStatusCode(),this.OK);
	}
	
	//este es el que va y el de usuario junto con asignar trabajos, etc.. en fin, lo que nos piden en la segunda entrega
	public void nuevosTrabajos(String titulo, String descripcion ) throws ClientProtocolException, IOException {
		String url = this.BASE_URL + "/trabajo";
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();
		jsonObject.put("titulo", titulo);
		jsonObject.put("descripcion", descripcion);
		String jsonString = jsonObject.toString();
		HttpPost post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		HttpResponse response = this.client.execute(post);
		assertEquals(response.getStatusLine().getStatusCode(),this.OK);
	}
}
