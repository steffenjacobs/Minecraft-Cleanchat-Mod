package me.steffenjacobs.cleanchatmod.config;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class ConfigManager {

    private CleanChatConfiguration config;

    public CleanChatConfiguration load() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("configuration-clean-chat.yaml");
        Map<String, Object> map = new Yaml().load(inputStream);

        config = CleanChatConfiguration.builder()
                .ignoreVoting((Boolean) map.get("ignore-voting"))
                .prefixVoting(map.get("prefix-voting").toString())
                .build();

        return config;
    }

    public CleanChatConfiguration getConfiguration() {
        if (config == null) {
            load();
        }
        return config;
    }
}
