package com.example.rikharthu;

import java.util.Arrays;

public class Record {

    public byte mb;
    public byte me;
    public byte cf;
    public byte sr;
    public byte il;
    public int tnf;

    public byte typeLength;
    public byte payloadLength;
    public byte idLength;
    public byte recordType;
    public byte id;
    public byte[] payload;

    public Record(byte[] payload) {
        // NDEF header is first byte of the payload
        byte header = payload[0];

        mb = (byte) ((header & 0b10000000) >> 7);
        me = (byte) ((header & 0b01000000) >> 6);
        cf = (byte) ((header & 0b00100000) >> 5);
        sr = (byte) ((header & 0b00010000) >> 4);
        il = (byte) ((header & 0b00001000) >> 3);
        tnf = (header & 0b00000111);
        byte[] type;

        typeLength = payload[1];
        payloadLength = payload[2];
        idLength = 0;
        byte offset = 0;
        if (il != 0) {
            offset = 1;
            idLength = payload[3];
        }
        recordType = payload[4 + offset];
        offset += idLength;
        this.payload = Arrays.copyOfRange(payload, 5 + offset, 5 + offset + payloadLength);
    }

    public Record() {
    }

    @Override
    public String toString() {
        return "mb=" + mb + ", me=" + me + ", " + new String(this.payload);
    }

    public byte[] toByteArray() {
        // TODO
        return null;
    }
}
