package com.teamsnapblock.snapblock.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "answerStr"
})
public class AnswerStr {

    @JsonProperty("answerStr")
    private String answerStr;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The answerStr
     */
    @JsonProperty("answerStr")
    public String getAnswerStr() {
        return answerStr;
    }

    /**
     *
     * @param answerStr
     * The answerStr
     */
    @JsonProperty("answerStr")
    public void setAnswerStr(String answerStr) {
        this.answerStr = answerStr;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}