package com.lee.util.tools;

import java.io.*;

/**
 * Created by leith on 2016/6/30.
 */
public class UrlDecodeCat {

    public int char2i(int c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'a' && c <= 'f') {
            return 10 + c - 'a';
        }
        if (c >= 'A' && c <= 'F') {
            return 10 + c - 'A';
        }
        throw new RuntimeException("why here?");
    }

    public void decode(InputStream input, PrintStream output) throws IOException {
        Reader reader = new InputStreamReader(new BufferedInputStream(input), "UTF-8");
        Writer writer = new OutputStreamWriter(output, "UTF-8");
        int c;
        int N = 1024 * 1024 * 3;
        int[] buffer = new int[N];
        byte[] bytes = new byte[N / 3];
        int len = 0;
        while ((c = reader.read()) != -1) {
            boolean flush = false;
            if (c == '%') {
                if ((len % 3) != 0) {
                    flush = true;
                }
            } else if (len > 0) {
                if ((len % 3) == 0) {
                    flush = true;
                } else if (!((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F'))) {
                    flush = true;
                }
            } else {
                flush = true;
            }
            if (flush) {
                if (len > 0) {
                    int a = len / 3;
                    for (int i = 0; i < a; i++) {
                        int c1 = buffer[3 * i + 1];
                        int c2 = buffer[3 * i + 2];
                        bytes[i] = (byte) (char2i(c1) * 16 + char2i(c2));
                    }
                    if (a > 0) {
                        writer.write(new String(bytes, 0, a, "UTF-8"));
                    }
                    for (int i = 3 * a; i < len; i++) {
                        writer.write(buffer[i]);
                    }
                }
                writer.write(c);
                writer.flush();
                len = 0;
            } else {
                buffer[len++] = c;
            }
            if (output.checkError()) {
                break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new UrlDecodeCat().decode(System.in, System.out);
    }

}
