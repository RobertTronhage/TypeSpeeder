/**
 * The Patch class represents a patch entity in the TypeSpeeder game.
 * It contains information about the patch version and the release date and time.
 *
 * @Author: Robert Tronhage
 */
package se.ju23.typespeeder.entity;

import jakarta.persistence.Entity;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

@Component
public class Patch {
    private String patchVersion = "";
    private LocalDateTime releaseDateTime;

    public Patch() {
        this.releaseDateTime = LocalDateTime.now();
    }

    public Patch(String patchVersion, LocalDateTime releaseDateTime) {
        this.patchVersion = getPatchVersionFromGitTag();
        this.releaseDateTime = LocalDateTime.now();
    }

    public String getPatchVersion() {
        return patchVersion;
    }

    public void setPatchVersion(String patchVersion) {
        this.patchVersion = patchVersion;
    }

    public LocalDateTime getReleaseDateTime() {
        return releaseDateTime;
    }

    public void setReleaseDateTime(LocalDateTime releaseDateTime) {
        this.releaseDateTime = releaseDateTime;
    }

    public static String getPatchVersionFromGitTag() {
        try {
            Process process = Runtime.getRuntime().exec("git describe --tags");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();

            return line;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Unknown";
    }


}