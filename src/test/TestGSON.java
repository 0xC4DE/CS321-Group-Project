package test;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class UserProfile {
    String name;

    public UserProfile(String jason) {
        name = jason;
    }
}

public class TestGSON extends Tester {

    public TestGSON() throws Exception {
        super();
    }

    public void run() throws IOException {
        List<UserProfile> list = new ArrayList<>();
        list.add(new UserProfile("Jason"));
        list.add(new UserProfile("Mark"));

        Gson gson = new Gson();
        FileWriter writer = new FileWriter(".testdata");
        gson.toJson(list, writer);
        writer.flush();
        writer.close();
    }
}
