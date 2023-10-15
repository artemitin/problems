package test.reactor;

public class FacadeNonReact {
    //may be injected
    ServiceLoad serviceLoad = new ServiceLoad();

    public String doWork(ServiceRequest request) {
        return serviceLoad.compute(request.getValue());
    }
}
