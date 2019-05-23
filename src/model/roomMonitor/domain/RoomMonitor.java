package model.roomMonitor.domain;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class RoomMonitor {
    public class Record {
        private LocalDate date;
        private boolean isFree;

        public Record() {
        }

        public Record(LocalDate date, boolean isFree) {
            this.date = date;
            this.isFree = isFree;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public boolean isFree() {
            return isFree;
        }

        public void setFree(boolean free) {
            isFree = free;
        }
    }


    private List<Record> records;

    public RoomMonitor() {
        records = new LinkedList<>();
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "RoomMonitor{" +
                "records=" + records +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomMonitor that = (RoomMonitor) o;
        return Objects.equals(records, that.records);
    }

    @Override
    public int hashCode() {
        return Objects.hash(records);
    }
}
