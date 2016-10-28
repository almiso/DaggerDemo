package org.almiso.daggerdemo.model;

public class UiUpdateEvent {

    private static int totalEvents = 1;
    public static final int onCustomerPresenterUpdated = totalEvents++;
    public static final int onColorPresenterUpdated = totalEvents++;

    /* Data */

    private int eventId;

    public UiUpdateEvent(int eventId) {
        this.eventId = eventId;
    }

    public int getEventId() {
        return eventId;
    }
}