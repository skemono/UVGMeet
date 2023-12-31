package com.uvgmeetf.uvgmeetf;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;
import com.google.firebase.cloud.FirestoreClient;

import com.google.api.core.ApiFuture;
import java.util.HashMap;
import java.util.Map;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

// Use the application default credentials


public class UVGMeetDB {

    private Firestore db;

    public UVGMeetDB() throws IOException {

        URL refreshToken = UVGMeetDB.class.getResource("uvgmeet-d6e95-firebase-adminsdk-m2nt0-dec0531392.json");

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(refreshToken.openStream()))
                .setDatabaseUrl("https://uvgmeet-d6e95.firebaseio.com/")
                .build();

        FirebaseApp.initializeApp(options);
        this.db = FirestoreClient.getFirestore();
    }

    // AGREGAR EN LA PRIMERA COLECCION Y PRIMER DOCUMENTO
    public void agregar(String coleccion, String documento, Map<String, Object> data) throws ExecutionException, InterruptedException {
        DocumentReference docRef = this.db.collection(coleccion).document(documento);
        ApiFuture<WriteResult> result = docRef.set(data);

        System.out.println("Update time : " + result.get().getUpdateTime());
    }

    public void eliminar(String coleccion, String documento) throws ExecutionException, InterruptedException {
        DocumentReference docRef = this.db.collection(coleccion).document(documento);
        ApiFuture<WriteResult> result = docRef.delete();

        System.out.println("Update time : " + result.get().getUpdateTime());
    }


    // AGREGAR EN SEGUNDA COLECCIONES Y SEGUNDO DOCUMENTO
    public void agregar(String coleccion, String documento, String coleccion2, String documento2, Map<String, Object> data) throws ExecutionException, InterruptedException {
        DocumentReference docRef = this.db.collection(coleccion).document(documento).collection(coleccion2).document(documento2);
        ApiFuture<WriteResult> result = docRef.set(data);

        System.out.println("Update time : " + result.get().getUpdateTime());
    }

    // LEER TODOS LOS DOCUMENTOS DE LA PRIMERA COLECCION
    public List<Map<String, Object>> leer(String coleccion) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = db.collection(coleccion).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Map<String, Object>> res = new ArrayList<>();

        for (QueryDocumentSnapshot document : documents) {
            res.add(document.getData());
        }
        return res;
    }

    // LEER UN DOCUMENTO DE LA PRIMERA COLECCION
    public Map<String, Object> leer (String coleccion, String documento) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection(coleccion).document(documento);
        ApiFuture<DocumentSnapshot> future = docRef.get();

        DocumentSnapshot document = future.get();
        return document.getData();
    }

    // LEER TODOS LOS DOCUMENTOS DE LA SEGUNDA COLECCION
    public List<Map<String, Object>> leer(String coleccion, String documento, String coleccion2) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = db.collection(coleccion).document(documento).collection(coleccion2).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Map<String, Object>> res = new ArrayList<>();

        for (QueryDocumentSnapshot document : documents) {
            res.add(document.getData());
        }
        return res;
    }

    // LEER NOMBRES DE TODOS LOS DOCUMENTOS DE UNA COLECCION

    public List<String> leerNombreDoc(String coleccion) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = db.collection(coleccion).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<String> res = new ArrayList<>();

        for (QueryDocumentSnapshot document : documents) {
            res.add(document.getId());
        }
        return res;
    }

    // LEER NOMBRES DE TODOS LOS DOCUMENTOS DE LA SEGUNDA COLECCION
    public List<String> leerNombreDoc(String coleccion, String documento, String coleccion2) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = db.collection(coleccion).document(documento).collection(coleccion2).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<String> res = new ArrayList<>();

        for (QueryDocumentSnapshot document : documents) {
            res.add(document.getId());
        }
        return res;
    }

    // LEER UN DOCUMENTO DE LA SEGUNDA COLECCION
    public Map<String, Object> leer (String coleccion, String documento, String coleccion2, String documento2) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection(coleccion).document(documento).collection(coleccion2).document(documento2);
        ApiFuture<DocumentSnapshot> future = docRef.get();

        DocumentSnapshot document = future.get();
        return document.getData();
    }

    // DETERMINAR ÍNDICE DEL SIGUIENTE DOCUMENTO DE LA PRIMERA COLECCION
    public int nextNumberOfDoc(String coleccion) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = db.collection(coleccion).get();
// future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        return documents.size() + 1;
    }

    // DETERMINAR ÍNDICE DEL SIGUIENTE DOCUMENTO DE LA SEGUNDA COLECCION
    public int nextNumberOfDoc(String coleccion, String documento, String coleccion2) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = db.collection(coleccion).document(documento).collection(coleccion2).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        return documents.size() + 1;
    }

    // ACTUALIZAR UN DOCUMENTO DENTRO DE UNA COLECCION
    public void actualizar(String coleccion, String documento, Map<String, Object> data) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection(coleccion).document(documento);
        ApiFuture<WriteResult> future = docRef.update(data);

        WriteResult result = future.get();
        System.out.println("Write result: " + result);
    }

    // ACTUALIZAR UN DOCUMENTO DENTRO DE DOS COLECCIONES
    public void actualizar(String coleccion, String documento, String coleccion2, String documento2, Map<String, Object> data) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection(coleccion).document(documento).collection(coleccion2).document(documento2);
        ApiFuture<WriteResult> future = docRef.update(data);

        WriteResult result = future.get();
        System.out.println("Write result: " + result);
    }

    // VERIFICAR EXISTENCIA EN UN DOCUMENTO DENTRO DE UNA COLECCION
    public String verificarExistencia(String coleccion, Object propiedad, Object comparacion) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = db.collection(coleccion).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        for (QueryDocumentSnapshot document : documents) {
            for (Map.Entry<String, Object> entry : document.getData().entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (key.equals(propiedad)){
                    System.out.println("Comparando " + comparacion + " : " + value);
                    if (value.toString().equals(comparacion.toString())){
                        return document.getId();
                    }
                }
            }

        }
        return "";
    }

    public String verificarExistenciaDos(String coleccion, Object propiedad, Object comparacion, Object propiedad2, Object comparacion2) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = db.collection(coleccion).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        int cumple2 = 0;
        String docId = "";

        for (QueryDocumentSnapshot document : documents) {
            for (Map.Entry<String, Object> entry : document.getData().entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (key.equals(propiedad)){
                    System.out.println("Comparando " + comparacion + " : " + value);
                    if (value.toString().equals(comparacion.toString())){
                        cumple2+=1;
                    }
                }else if (key.equals(propiedad2)){
                    if (value.toString().equals(comparacion2.toString())){
                        cumple2+=1;
                    }
                }
                docId = document.getId();
            }

        }
        if (cumple2 ==2){return docId;}
        return "";
    }

    // VERIFICAR EXISTENCIA EN UN DOCUMENTO DENTRO DE DOS COLECCIONES
    public String verificarExistencia(String coleccion, String documento, String coleccion2, Object propiedad, Object comparacion) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = db.collection(coleccion).document(documento).collection(coleccion2).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        for (QueryDocumentSnapshot document : documents) {
            for (Map.Entry<String, Object> entry : document.getData().entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                System.out.println(key + ": " + value);
                if (key.equals(propiedad)){
                    if (value.toString().equals(comparacion.toString())){
                        return document.getId();
                    }
                }
            }
        }
        return "";
    }

    // VERIFICAR EXISTENCIA EN UN DOCUMENTO DENTRO DE UNA COLECCION
    public String verificarExistenciaDoc(String coleccion, String comparacion) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = db.collection(coleccion).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        for (QueryDocumentSnapshot document : documents) {
            if (document.getId().equals(comparacion)){
                return document.getId();
            }
        }
        return "";
    }

    // VERIFICAR EXISTENCIA EN UN DOCUMENTO DENTRO DE DOS COLECCIONNES
    public String verficarExistenciaDoc(String coleccion, String documento, String coleccion2, String comparacion) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = db.collection(coleccion).document(documento).collection(coleccion2).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        for (QueryDocumentSnapshot document : documents) {
            if (document.getId().equals(comparacion)){
                return document.getId();
            }
        }
        return "";
    }
}

