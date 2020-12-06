package me.steffenjacobs.cleanchatmod.config;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CleanChatConfiguration {
    boolean ignoreVoting;
    String prefixVoting;
}
