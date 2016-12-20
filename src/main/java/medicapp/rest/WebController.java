/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package medicapp.rest;

import com.mongodb.*;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple REST service which is able to say hello to someone using HelloService Please take a look at the web.xml where JAX-RS
 * is enabled
 *
 * @author gbrey@redhat.com
 *
 */

@Path("/")
public class WebController {


    @GET
    @Path("/json")
    @Produces({ "application/json" })
    public String getHelloWorldJSON() {
        return "{\"result\":\"" + "Hello World" + "\"}";
    }

    @GET
    @Path("/test/{message}")
    @Produces("text/plain")
    public String showMsg(@PathParam("message") String message){
        return message;
    }

    @GET
    @Path("/getDoctor")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Users> getUsers() {
        MongoDBSingleton dbSingleton = MongoDBSingleton.getInstance();
        DB db = dbSingleton.getTestdb();
        DBCollection coll = db.getCollection("users");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("statut", "Docteur");
        DBCursor cursor = coll.find(searchQuery);
        List<Users> list = new ArrayList<Users>();
        while (cursor.hasNext()) {
            DBObject o = cursor.next();
            Users users = new Users();
            users.setPrenom((String) o.get("prenom"));
            users.setNom((String) o.get("nom"));
            users.setStatut((String) o.get("statut"));
            users.setLogin((String) o.get("login"));
            users.setPwd((String) o.get("pwd"));
            list.add(users);
        }
        return list;
    }

}
