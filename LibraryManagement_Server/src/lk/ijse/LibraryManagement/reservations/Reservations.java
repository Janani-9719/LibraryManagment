package lk.ijse.LibraryManagement.reservations;

import java.util.HashMap;

public class Reservations<T> {

    private static class ResMap<T>{
        private T service;
        private boolean isReserved;

        public ResMap(T service,boolean isReserved){
            this.service=service;
            this.isReserved=isReserved;
        }

        public T getService(){
            return service;
        }

        public void setService(T service){
            this.service=service;
        }

        public boolean isIsReserved(){
            return isReserved;
        }

        public void setIsReserved(boolean isReserved){
            this.isReserved=isReserved;
        }
    }

    HashMap<Object, ResMap<T>> reservationMap = new HashMap<>();

    public boolean reserve(Object key, T service, boolean isReserved) {
        if (reservationMap.containsKey(key)) {
            return reservationMap.get(key).getService() == service;
        } else {
            reservationMap.put(key, new ResMap<T>(service, isReserved));
            return true;
        }
    }

    public boolean release(Object key, T service) {
        if (reservationMap.containsKey(key) && reservationMap.get(key).getService() == service) {
            reservationMap.remove(key);
            return true;
        }
        return false;
    }

    public boolean checkStatus(Object key, T service) {
        if (reservationMap.containsKey(key) && reservationMap.get(key).getService() == service) {
            return reservationMap.get(key).isIsReserved();
        } else {
            return false;
        }
    }

}
