package HajimeAPI4J.responses;

import java.util.Collection;
import java.util.List;

import HajimeAPI4J.responses.interfaces.ResponseAdapter;

public class ResponseListProperty {
    
    //Declare field
    private Collection<? extends ListMusicResponse> responseList;

    public Collection<? extends ListMusicResponse> getResponseList() {
        return responseList;
    }

    public void setResponseList(Collection<? extends ListMusicResponse> responseList) {
        this.responseList = responseList;
    }

}
