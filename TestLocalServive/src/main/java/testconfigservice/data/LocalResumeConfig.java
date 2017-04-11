package testconfigservice.data;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 11.04.2017.
 */
@ToString
public class LocalResumeConfig {

    private String name;

    private List<String> profiles;

    private String label;

    private String version;

    private List<Object> propertySources;


    public LocalResumeConfig() {
        List<String> profiles = new ArrayList<>();
        profiles.add("default");

        Map<String, String> configServerGitUri = new HashMap<String, String>();
        configServerGitUri.put("name","C:///config-repo/a-bootiful-client.properties");

        List<Object> propertySources = new ArrayList<>();
        propertySources.add(configServerGitUri);

        this.name = "first";
        this.label = "master";
        this.version = "v465627094279";
        this.profiles = profiles;
        this.propertySources = propertySources;
    }
}
