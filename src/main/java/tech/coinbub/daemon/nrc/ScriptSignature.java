package tech.coinbub.daemon.nrc;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScriptSignature {
    public String asm;
    public String hex;
}
