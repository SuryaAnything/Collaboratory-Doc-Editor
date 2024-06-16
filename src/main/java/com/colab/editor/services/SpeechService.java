package com.colab.editor.services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;

@Service
public class SpeechService {

    private static final String VOICE_ID_MALE = "29vD33N1CtxCmqQRPOHJ"; 
    private static final String VOICE_ID_FEMALE = "21m00Tcm4TlvDq8ikWAM";
    private static final String API_ENDPOINT = "https://api.elevenlabs.io/v1/text-to-speech/{voice_id}";

    public boolean textToSpeech(String content, String type, String filePath) {
        String voiceId = type.equalsIgnoreCase("male") ? VOICE_ID_MALE : VOICE_ID_FEMALE;
        String requestBody = String.format(
            "{\n" +
            "  \"text\": \"%s\",\n" +
            "  \"model_id\": \"eleven_multilingual_v2\",\n" + 
            "  \"voice_settings\": {\n" +
            "    \"stability\": 0.5,\n" +
            "    \"similarity_boost\": 0.5,\n" +
            "    \"style\": 0,\n" +
            "    \"use_speaker_boost\": true\n" +
            "  },\n" +
            "  \"pronunciation_dictionary_locators\": [],\n" +
            "  \"seed\": null,\n" +
            "  \"previous_text\": null,\n" +
            "  \"next_text\": null,\n" +
            "  \"previous_request_ids\": [],\n" +
            "  \"next_request_ids\": []\n" +
            "}", content);

        try {
            HttpResponse<InputStream> response = Unirest.post(API_ENDPOINT)
                    .routeParam("voice_id", voiceId)
                    .header("xi-api-key", "7a4d39efcb195312e3486fef12748396")
                    .header("Content-Type", "application/json")
                    .body(requestBody)
                    .asObject(InputStream.class);

            if (response.getStatus() == 200) {
                Path path = Paths.get(filePath);
                try (InputStream is = response.getBody();
                     FileOutputStream fos = new FileOutputStream(path.toFile())) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = is.read(buffer)) != -1) {
                        fos.write(buffer, 0, bytesRead);
                    }
                    return true;
                } catch (IOException e) {
                    System.err.println("Failed to write to file: " + e.getMessage());
                    e.printStackTrace();
                    return false;
                }
            } else {
                System.err.println("Failed to download file: HTTP status " + response.getStatus());
                System.err.println("Response body: " + response.getStatusText());
                return false;
            }
        } catch (UnirestException e) {
            System.err.println("Unirest exception: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
