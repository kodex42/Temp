//package com.example.a1449877.assignment4gson;
//
//// NOTE TO STUDENTS: use Gson not org.json libraries.
//import com.example.a1449877.assignment4gson.Model.Server.HttpRequest;
//import com.example.a1449877.assignment4gson.Model.Server.HttpResponse;
//import com.example.a1449877.assignment4gson.Model.Server.Note;
//import com.example.a1449877.assignment4gson.Model.Server.User;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.json.JSONTokener;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.HashSet;
//import java.util.Scanner;
//import java.util.Set;
//
//import ca.qc.johnabbott.cs.cs616.notes.model.server.HttpRequest;
//import ca.qc.johnabbott.cs.cs616.notes.model.server.HttpResponse;
//import ca.qc.johnabbott.cs.cs616.notes.model.server.Note;
//import ca.qc.johnabbott.cs.cs616.notes.model.server.User;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;
//
///**
// * Tests of the server interaction for user and note repositories.
// * @author Ian Clement (ian.clement@johnabbott.qc.ca)
// */
//public class TestServer {
//
//    // server parameters: change as needed
//    public static final String SERVER = "localhost";
//    public static final int PORT = 9999;
//    public static final String PREFIX = "http://" + SERVER + ":" + String.valueOf(PORT);
//
//    // sample values used in many tests
//    private User sampleUser;
//    private Note sampleNote;
//    private String userUrl;
//    private String[] noteUrls;
//
//    /**
//     * Create a sample user and a few notes
//     *
//     * @throws IOException
//     */
//    @Before
//    public void setUp() throws Exception {
//
//        // in case any data was left over from the last request
//        tearDown();
//
//        HttpResponse response = HttpRequest.make(PREFIX + "/user", HttpRequest.Method.POST, "{\"name\" : \"ian\", \"password\": \"8843d7f92416211de9ebb963ff4ce28125932878\", \"email\": \"ian.clement@johnabbott.qc.ca\"}");
//        if(response.getStatus() != 201)
//            throw new Exception("Error: " + response);
//
//        userUrl = response.getHeader("Location").get(0);
//
//        String[] notesJson = new String[] {
//                "{\"title\":\"Lorem ipsum dolor\",\"body\":\"Lorem ipsum dolor sit amet, vel ei graece primis ullamcorper, unum denique an nam. Eum fabulas impedit tibique ex. No nonumes lobortis usu, te probo partem consequat vel. An dicta fastidii iracundia mel, eum pertinax consequat id. Est commune sadipscing ex, vocent laoreet an ius.\",\"category\":-737419,\"reminder\":\"2016-10-12T16:32:54.000-0400\",\"created\":\"2016-09-10T00:23:34.000-0400\", \"createdBy\": \""+ userUrl + "\"}\n",
//                "{\"title\":\"Nullam disputando eam\",\"body\":\"Nullam disputando eam at, ullamcorper conclusionemque sed ad. Sit urbanitas adolescens cu, elit saepe ei nam. Latine voluptua adipisci sed ei. Per eu nostro eruditi sanctus, ad duo eleifend mediocrem definiebas, usu cibo commodo euripidis id.\",\"category\":-10036753,\"reminder\":\"2016-10-10T21:32:43.000-0400\",\"created\":\"2016-09-10T00:24:34.000-0400\", \"createdBy\": \""+ userUrl + "\"}\n",
//                "{\"title\":\"Pro civibus salutatus\",\"body\":\"Pro civibus salutatus at, eum ei propriae accusamus, duo vidisse prompta ne. Has movet ocurreret elaboraret in, choro accommodare ne sea. Vel assum albucius nominati no. Te nam quem impetus, graeci intellegam mea ea.\",\"category\":-10036753,\"reminder\":null,\"created\":\"2016-09-10T00:25:34.000-0400\", \"createdBy\": \""+ userUrl + "\"}\n",
//                "{\"title\":\"An commodo legimus lucilius\",\"body\":\"An commodo legimus lucilius cum, cu clita noluisse apeirian duo. Cu sanctus blandit splendide per. Duo no assum vidisse deleniti. Integre similique assueverit ne eum, ad mei admodum fuisset similique, zril saepe theophrastus vim ut. Ea tation omittam principes has. Id nec consequat adversarium, ne pri ipsum numquam.\",\"category\":-448910,\"reminder\":\"2016-10-13T00:12:12.000-0400\",\"created\":\"2016-09-10T00:26:34.000-0400\", \"createdBy\": \""+ userUrl + "\"}\n",
//                "{\"title\":\"Te magna animal civibus\",\"body\":\"Te magna animal civibus cum, assum efficiantur mel id. At nec meis oportere, nihil quidam temporibus mei ad. Nec suas convenire ea, ad qui numquam copiosae. Amet vide possit et has. Vim elitr maiorum voluptatibus te.\",\"category\":-3381709,\"reminder\":\"2016-10-12T16:32:54.000-0400\",\"created\":\"2016-09-10T00:27:34.000-0400\", \"createdBy\": \""+ userUrl + "\"}\n"
//        };
//
//        // store note URLs
//        noteUrls = new String[notesJson.length];
//
//        int i=0;
//        for(String json : notesJson) {
//            HttpResponse response1 = HttpRequest.make(PREFIX + "/note", HttpRequest.Method.POST, json);
//            if(response1.getStatus() != 201)
//                throw new Exception("Error: " + response1);
//
//            noteUrls[i++] = response1.getHeader("Location").get(0);
//        }
//
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
//        sampleNote = new Note();
//        sampleNote.setUrl(noteUrls[0]);
//        sampleNote.setTitle("Lorem ipsum dolor");
//        sampleNote.setBody("Lorem ipsum dolor sit amet, vel ei graece primis ullamcorper, unum denique an nam. Eum fabulas impedit tibique ex. No nonumes lobortis usu, te probo partem consequat vel. An dicta fastidii iracundia mel, eum pertinax consequat id. Est commune sadipscing ex, vocent laoreet an ius.");
//        sampleNote.setCategory(-737419);
//        sampleNote.setReminder(format.parse("2016-10-12T16:32:54.000-0400"));
//        sampleNote.setCreated(format.parse("2016-09-10T00:23:34.000-0400"));
//
//        sampleUser = new User();
//        sampleUser.setUrl(response.getHeader("Location").get(0));
//        sampleUser.setName("ian");
//        sampleUser.setPassword("foobar");
//        sampleUser.setEmail("ian.clement@johnabbott.qc.ca");
//    }
//
//
//
//    /**
//     * Remove all users and notes from the server: restore server to initial state
//     * - Note: autoincremented IDs will always be different.
//     * @throws Exception
//     */
//    @After
//    public void tearDown() throws Exception {
//
//        String[] repos = new String[] {"note", "user"}; // The order matters due to foreign key contraints
//
//        for(String repo : repos) {
//
//            // list all items in the repo
//            HttpResponse response = HttpRequest.make(PREFIX + "/" + repo, HttpRequest.Method.GET);
//            if (response.getStatus() != 200)
//                throw new IOException("Cannot get /" + repo + " repository. Something is wrong, please restart server.");
//
//            // scan the response and extract all "self" urls.
//            Scanner scanner = new Scanner(response.getBody());
//            Set<String> urls = new HashSet<>();
//            while(scanner.hasNext()) {
//                String line = scanner.nextLine();
//                if(line.contains("href")) {
//                    // remove all double-quotes, and the "href :"  prefix
//                    line = line.replace("\"", "");
//                    line = line.replaceFirst("^.*href : ","");
//                    // only take "self" URLs
//                    if(line.matches("^" + PREFIX + "/" + repo + "/[\\d]+"))
//                        urls.add(line);
//                }
//            }
//
//            // delete all elements found in the repo
//            for(String url : urls) {
//                HttpResponse deleteResponse = HttpRequest.make(url, HttpRequest.Method.DELETE);
//                if (deleteResponse.getStatus() != 204)
//                    throw new Exception("Could not delete " + url + ". Please restart server.");
//            }
//
//        }
//    }
//
//    /**
//     * Testing that we can connect to server.
//     *
//     * @throws IOException
//     */
//    @Test
//    public void testPingServer() {
//        HttpResponse response = null;
//        try {
//            response = HttpRequest.make(PREFIX, HttpRequest.Method.GET);
//        } catch (IOException e) {
//            fail();
//        }
//        assertEquals(response.getStatus(), 200);
//    }
//
//    /**
//     * Test: read a user
//     *
//     * @throws IOException
//     */
//    @Test
//    public void testReadUser() throws IOException {
//        HttpResponse response = HttpRequest.make(sampleUser.getUrl(), HttpRequest.Method.GET);
//        assertEquals(200, response.getStatus());
//
//        User receivedUser = User.parse(response.getBody());
//        assertEquals(sampleUser.getUrl(), receivedUser.getUrl());
//        assertEquals(sampleUser.getName(), receivedUser.getName());
//        assertEquals(sampleUser.getPassword(), receivedUser.getPassword());
//        assertEquals(sampleUser.getEmail(), receivedUser.getEmail());
//    }
//
//    /**
//     * Test: create a user
//     *
//     * @throws IOException
//     */
//    @Test
//    public void testCreateUser() throws IOException {
//
//        // regular user
//        User sampleUser = new User();
//        sampleUser.setName("Foo");
//        sampleUser.setPassword("12345");
//        sampleUser.setEmail("foo@foo.com");
//
//        HttpResponse response = HttpRequest.make(PREFIX + "/user", HttpRequest.Method.POST, sampleUser.format());
//        assertEquals(201, response.getStatus());
//
//        // with optional fields
//        User sampleUser2 = new User();
//        sampleUser2.setName("Bar");
//
//        response = HttpRequest.make(PREFIX + "/user", HttpRequest.Method.POST, sampleUser2.format());
//        assertEquals(409, response.getStatus());
//
//        User sampleUser3 = new User();
//        sampleUser3.setName("Quux");
//        sampleUser3.setPassword("12345");
//        response = HttpRequest.make(PREFIX + "/user", HttpRequest.Method.POST, sampleUser3.format());
//        assertEquals(201, response.getStatus());
//    }
//
//
//    /**
//     * Test: update a user
//     *
//     * @throws IOException
//     */
//    @Test
//    public void testUpdateUser() throws IOException {
//
//        HttpResponse response = HttpRequest.make(sampleUser.getUrl(), HttpRequest.Method.GET);
//        assertEquals(200, response.getStatus());
//        User receivedUser = User.parse(response.getBody());
//
//        // update user object locally
//        receivedUser.setName("Foo");
//        receivedUser.setPassword("bar");
//        receivedUser.setEmail("foo@foo.com");
//
//        // push updates to server
//        response = HttpRequest.make(receivedUser.getUrl(), HttpRequest.Method.PUT, receivedUser.format());
//        assertEquals(204, response.getStatus());
//    }
//
//    /**
//     * Test: delete a user
//     *
//     * @throws IOException
//     */
//    @Test
//    public void testDeleteUser() throws IOException {
//
//        // first delete fails due to FK constraint
//        HttpResponse response = HttpRequest.make(sampleUser.getUrl(), HttpRequest.Method.DELETE);
//        assertEquals(409, response.getStatus());
//
//        for(String url: noteUrls)
//          HttpRequest.make(url, HttpRequest.Method.DELETE);
//        response = HttpRequest.make(sampleUser.getUrl(), HttpRequest.Method.DELETE);
//        assertEquals(204, response.getStatus());
//
//        // deleted resource is no longer on the server
//        response = HttpRequest.make(sampleUser.getUrl(), HttpRequest.Method.DELETE);
//        assertEquals(404, response.getStatus());
//
//    }
//
//    /**
//     * Test: unique names values
//     *
//     * @throws IOException
//     */
//    @Test
//    public void testUserNameUnique() throws IOException {
//        fail(); // TODO
//    }
//
//    /**
//     * Test: unique note titles
//     *
//     * @throws IOException
//     */
//    @Test
//    public void testNoteTitleUnique() throws IOException {
//        fail(); // TODO
//    }
//
//    /**
//     * Test: note reminders are after their creation time
//     *
//     * @throws IOException
//     */
//    @Test
//    public void testNoteReminderAfterCreated() throws IOException {
//        fail(); // TODO
//    }
//}
