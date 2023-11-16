package com.uvgmeetf.uvgmeetf;


import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.storage.Blob;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class UVGMeetStorage {
    private static Credentials credentials;

    public UVGMeetStorage() throws IOException {
        URL adc = UVGMeetDB.class.getResource("application_default_credentials.json");
        credentials = GoogleCredentials.fromStream(adc.openStream());
    }

    public static void uploadObject(String bucketName, String objectName, String filePath) throws IOException {
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        BlobId blobId = BlobId.of(bucketName, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        Storage.BlobWriteOption precondition;
        if (storage.get(bucketName, objectName) == null) {
            precondition = Storage.BlobWriteOption.doesNotExist();
        } else {
            precondition =
                    Storage.BlobWriteOption.generationMatch(
                            storage.get(bucketName, objectName).getGeneration());
        }
        storage.createFrom(blobInfo, Paths.get(filePath), precondition);

        System.out.println(
                "File " + filePath + " uploaded to bucket " + bucketName + " as " + objectName);
    }

    public static void downloadObject(String bucketName, String objectName, String destFilePath) {

        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

        Blob blob = storage.get(BlobId.of(bucketName, objectName));
        blob.downloadTo(Paths.get(destFilePath));
        System.out.println(
                "Downloaded object "
                        + objectName
                        + " from bucket name "
                        + bucketName
                        + " to "
                        + destFilePath);
    }
    public static void deleteObject(String bucketName, String objectName) {

        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        Blob blob = storage.get(bucketName, objectName);
        if (blob == null) {
            System.out.println("The object " + objectName + " wasn't found in " + bucketName);
            return;
        }

        Storage.BlobSourceOption precondition =
                Storage.BlobSourceOption.generationMatch(blob.getGeneration());

        storage.delete(bucketName, objectName, precondition);

        System.out.println("Object " + objectName + " was deleted from " + bucketName);
    }
}