package ztml.dev.ngokhacbac.autotool.model;

public interface RequestResponeListenner {
    void RequestSuccess(String response);

    void RequestError();
}
