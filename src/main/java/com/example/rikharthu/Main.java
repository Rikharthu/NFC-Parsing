package com.example.rikharthu;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        byte[] payload = new byte[]{
                -111, 1, 15, 84, 2, 101, 110, -48, -65, -47, -128, -48, -72, -48, -78, -48, -75, -47, -126,
                17, 1, 8, 84, 2, 101, 110, 72, 101, 108, 108, 111,
                81, 1, 17, 85, 0, 109, 121, 58, 47, 47, 101, 120, 97, 109, 112, 108, 101, 46, 99, 111, 109,};


        byte b = 17;
        System.out.println(Integer.toBinaryString(b&0b11111111));

        Message message = new Message(payload);

        Message message2 = new Message(new byte[]{
                -111 | 0b01000000, 1, 15, 84, 2, 101, 110, -48, -65, -47, -128, -48, -72, -48, -78, -48, -75, -47, -126});

        System.out.println(payload);
    }
}
