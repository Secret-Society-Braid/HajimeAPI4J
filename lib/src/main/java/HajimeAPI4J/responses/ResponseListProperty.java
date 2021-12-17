package HajimeAPI4J.responses;

import java.util.List;
import java.util.Map;

public class ResponseListProperty {
    
    //Declare field
    private List<Map<String, Object>> responseList;

    public List<Map<String, Object>> getResponseList() {
        return responseList;
    }

    public void setResponseList(List<Map<String, Object>> responseList) {
        this.responseList = responseList;
    }

}
