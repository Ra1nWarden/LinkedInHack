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
        "answerPic"
})
public class AnswerPic {

    @JsonProperty("answerPic")
    private String answerPic;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The answerPic
     */
    @JsonProperty("answerPic")
    public String getAnswerPic() {
        return answerPic;
    }

    /**
     *
     * @param answerPic
     * The answerPic
     */
    @JsonProperty("answerPic")
    public void setAnswerPic(String answerPic) {
        this.answerPic = answerPic;
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