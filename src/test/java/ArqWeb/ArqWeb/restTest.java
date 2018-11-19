package ArqWeb.ArqWeb;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class restTest {
	
	private final String BASE_URL="http://localhost:8080/ArqWeb/api";
	
	private final int OK = 201;

	private final HttpClient client = HttpClientBuilder.create().build();
	
	@BeforeTest
    public void testInit(){
		System.out.println("restTest");
    }
	
	@Test
	public void serverIsUp() throws ClientProtocolException, IOException {
		System.out.println("serverIsUp");
		String url = this.BASE_URL + "/palabraClave/1";
		HttpGet get = new HttpGet(url);
		HttpResponse response = this.client.execute(get);
		assertEquals(this.OK,response.getStatusLine().getStatusCode());
	}
	
//	@Test(dependsOnMethods= {"serverIsUp"})
//	public void testPalabrasClaves() throws ClientProtocolException, IOException {
//		System.out.println("testPalabrasClaves");
//		this.nuevasPalabrasClave("Ruby",false);
//	}
	
//	@Test(dependsOnMethods= {"serverIsUp"})
//	public void testTrabajos() throws ClientProtocolException, IOException {
//		this.nuevosTrabajos("ERP", "Enterprise resource planning");
//		this.nuevosTrabajos("CRM", "Customer relationship management");
//		this.nuevosTrabajos("MRP", "Material requirements planning");
//	}
	
	@Test(dependsOnMethods= {"serverIsUp"})
	public void testNewUser() throws ClientProtocolException, IOException {
		System.out.println("testNewUser");
		this.newUser("Agustin", "Diaz");
	}
	
	@Test(dependsOnMethods= {"testNewUser"})
	public void testEditUser() throws ClientProtocolException, IOException {
		System.out.println("testEditUser");
		this.editUser("Joaquin", "Lardapide","1");
	}
	
//	@Test(dependsOnMethods= {"testEditUser"})
//	public void testAsignarTrabajo() throws ClientProtocolException, IOException {
//		System.out.println("testAsignarTrabajo");
//		this.asignarTrabajo("1","1");
//	}
	
//	@Test(dependsOnMethods= {"testEditUser"})
//	public void testGetTrabajosEnviados() throws ClientProtocolException, IOException {
//		System.out.println("testGetTrabajosEnviados");
//		this.getTrabajosEnviados("1");
//	}
	
//	@Test(dependsOnMethods= {"testEditUser"})
//	public void testGetTrabajosRevisadosEnRango() throws ClientProtocolException, IOException {
//		System.out.println("testGetTrabajosRevisadosEnRango");
//		this.getTrabajosRevisados("1","05-06-2018","22-12-2018");
//	}
	
	private void getTrabajosEnviados(String id) throws ClientProtocolException, IOException {
		String url = this.BASE_URL + "/usuario/trabajosEnviados/"+id;
		HttpGet get = new HttpGet(url);
		HttpResponse response = this.client.execute(get);
		assertEquals(response.getStatusLine().getStatusCode(),this.OK);
	}
	
	private void getTrabajosRevisados(String id,String fecha_inicio,String fecha_fin) throws ClientProtocolException, IOException {
		String url = this.BASE_URL + "/usuario/trabajosRevisados/"+id+"/"+fecha_inicio+"/"+fecha_fin;
		HttpGet get = new HttpGet(url);
		HttpResponse response = this.client.execute(get);
		assertEquals(response.getStatusLine().getStatusCode(),this.OK);
	}
	
	private void aceptarTrabajo(String usuario_id, String trabajo_id) throws ClientProtocolException, IOException {
		String url = this.BASE_URL + "/usuario/aceptar/"+usuario_id+"/"+trabajo_id;
		HttpPost post = new HttpPost(url);
		HttpResponse response = this.client.execute(post);
		assertEquals(response.getStatusLine().getStatusCode(),this.OK);
	}
	
	private void asignarTrabajo(String usuario_id, String trabajo_id) throws ClientProtocolException, IOException {
		String url = this.BASE_URL + "/usuario/asignar/"+usuario_id+"/"+trabajo_id;
		HttpPost post = new HttpPost(url);
		post.setEntity(new StringEntity("", ContentType.APPLICATION_JSON));
		HttpResponse response = this.client.execute(post);
		assertEquals(response.getStatusLine().getStatusCode(),this.OK);
		this.aceptarTrabajo(usuario_id,trabajo_id);
	}
	
	private void newUser(String nombre, String apellido) throws ClientProtocolException, IOException {
		String url = this.BASE_URL + "/usuario/";
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();
		jsonObject.put("nombre", nombre);
		jsonObject.put("apellido", apellido);
		String jsonString = jsonObject.toString();
		HttpPost post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		HttpResponse response = this.client.execute(post);
		assertEquals(response.getStatusLine().getStatusCode(),this.OK);
	}
	
	private void editUser(String nombre, String apellido, String id) throws ClientProtocolException, IOException {
		String url = this.BASE_URL + "/usuario/"+id;
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();
		jsonObject.put("nombre", nombre);
		jsonObject.put("apellido", apellido);
		String jsonString = jsonObject.toString();
		HttpPut put = new HttpPut(url);
		put.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		HttpResponse response = this.client.execute(put);
		assertEquals(response.getStatusLine().getStatusCode(),this.OK);
	}
	
	public void nuevasPalabrasClave(String palabra, Boolean esExperto ) throws ClientProtocolException, IOException {
		String url = this.BASE_URL + "/palabraClave";
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();
		jsonObject.put("palabra", palabra);
		jsonObject.put("esExperto", esExperto);
		String jsonString = jsonObject.toString();
		HttpPost post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		HttpResponse response = this.client.execute(post);
		assertEquals(response.getStatusLine().getStatusCode(),this.OK);
	}
	
	public void nuevosTrabajos(String titulo, String descripcion ) throws ClientProtocolException, IOException {
		String url = this.BASE_URL + "/trabajo";
		System.out.println(titulo+" "+descripcion);
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
