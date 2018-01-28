package com.example.rikharthu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Message {

    public final Record[] records;

//    public Record[] records;

    public Message(byte[] payload) {
        // Get recor count
        int recordsCount = 0;

        List<Record> records = new ArrayList<>();

        boolean stop = false;
        int offset = 0;
        do {
            int currentPos = 0;
            Record record = new Record();
            byte header = payload[offset + currentPos++];
            int recordLength = 0;
            record.mb = (byte) ((header & 0b10000000) >> 7);
            record.me = (byte) ((header & 0b01000000) >> 6);
            record.cf = (byte) ((header & 0b00100000) >> 5);
            record.sr = (byte) ((header & 0b00010000) >> 4);
            record.il = (byte) ((header & 0b00001000) >> 3);
            record.tnf = (header & 0b00000111);
            recordLength += 1;
            record.typeLength = payload[offset + currentPos++];
            recordLength += 1 + record.typeLength;

            record.payloadLength = payload[offset + currentPos++];
            recordLength += 1 + record.payloadLength;
            record.idLength = 0;


            if (record.il != 0) {
                record.idLength = payload[offset + currentPos++];
                // offset += 1 + record.idLength;
            }

            if (record.typeLength > 0) {
                // offset += 1 + record.typeLength;
                currentPos += record.typeLength;
            }
            record.payload = Arrays.copyOfRange(payload, offset + currentPos, offset + currentPos + record.payloadLength - 1);
            currentPos += record.payloadLength;

            records.add(record);
            offset += currentPos;
            stop = record.me == 1;
        } while (!stop);

        this.records = records.toArray(new Record[records.size()]);
    }
}
