package com.ran.util;

import java.util.Arrays;

public class Base64New {
    public static final String encode_str(byte abyte0[])
    {
        return new String(encode(abyte0));
    }	
	
    private static char[] encode(byte abyte0[])
    {
        int i = 0;
        int j;
        char ac1[];
        if(abyte0 != null)
            j = abyte0.length;
        else
            j = i;
        if(j == 0)
        {
            ac1 = new char[i];
        } else
        {
            int k = 3 * (j / 3);
            int l = 1 + (j + -1) / 3 << 2;
            int i1 = l + ((l + -1) / 76 << 1);
            char ac[] = new char[i1];
            int j1 = i;
            int k1 = i;
            int l1 = i;
            do
            {
                if(l1 >= k)
                    break;
                int i3 = l1 + 1;
                int j3 = (0xff & abyte0[l1]) << 16;
                int k3 = i3 + 1;
                int l3 = j3 | (0xff & abyte0[i3]) << 8;
                l1 = k3 + 1;
                int i4 = l3 | 0xff & abyte0[k3];
                int j4 = k1 + 1;
                ac[k1] = a[0x3f & i4 >>> 18];
                int k4 = j4 + 1;
                ac[j4] = a[0x3f & i4 >>> 12];
                int l4 = k4 + 1;
                ac[k4] = a[0x3f & i4 >>> 6];
                k1 = l4 + 1;
                ac[l4] = a[i4 & 0x3f];
                if(++j1 == 19 && k1 < i1 + -2)
                {
                    int i5 = k1 + 1;
                    ac[k1] = '\r';
                    int j5 = i5 + 1;
                    ac[i5] = '\n';
                    k1 = j5;
                    j1 = i;
                }
            } while(true);
            int i2 = j - k;
            if(i2 > 0)
            {
                int j2 = (0xff & abyte0[k]) << 10;
                if(i2 == 2)
                    i = (0xff & abyte0[j + -1]) << 2;
                int k2 = i | j2;
                ac[i1 + -4] = a[k2 >> 12];
                ac[i1 + -3] = a[0x3f & k2 >>> 6];
                int l2 = i1 + -2;
                char c;
                if(i2 == 2)
                    c = a[k2 & 0x3f];
                else
                    c = '=';
                ac[l2] = c;
                ac[i1 + -1] = '=';
            }
            ac1 = ac;
        }
        return ac1;
    }	
	
    public static final byte[] decode(String s)
    {
        int i;
        byte abyte1[];
        if(s != null)
            i = s.length();
        else
            i = 0;
        if(i == 0)
        {
            abyte1 = new byte[0];
        } else
        {
            int j = 0;
            int k = 0;
            for(; j < i; j++)
                if(b[s.charAt(j)] < 0)
                    k++;

            if((i - k) % 4 != 0)
            {
                abyte1 = null;
            } else
            {
                int l = i;
                int i1 = 0;
                do
                {
                    if(l <= 1)
                        break;
                    int ai1[] = b;
                    l--;
                    if(ai1[s.charAt(l)] > 0)
                        break;
                    if(s.charAt(l) == '=')
                        i1++;
                } while(true);
                int j1 = (6 * (i - k) >> 3) - i1;
                byte abyte0[] = new byte[j1];
                int k1 = 0;
                int l1 = 0;
                while(k1 < j1) 
                {
                    int i2 = 0;
                    int j2 = l1;
                    int k2 = 0;
                    while(k2 < 4) 
                    {
                        int ai[] = b;
                        int j3 = j2 + 1;
                        int k3 = ai[s.charAt(j2)];
                        if(k3 >= 0)
                            i2 |= k3 << 18 - k2 * 6;
                        else
                            k2--;
                        k2++;
                        j2 = j3;
                    }
                    int l2 = k1 + 1;
                    abyte0[k1] = (byte)(i2 >> 16);
                    if(l2 < j1)
                    {
                        int i3 = l2 + 1;
                        abyte0[l2] = (byte)(i2 >> 8);
                        if(i3 < j1)
                        {
                            l2 = i3 + 1;
                            abyte0[i3] = (byte)i2;
                        } else
                        {
                            l2 = i3;
                        }
                    }
                    k1 = l2;
                    l1 = j2;
                }
                abyte1 = abyte0;
            }
        }
        return abyte1;
    }    
    
    private static final char a[];
    private static final int b[];

    static 
    {
        a = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
        int ai[] = new int[256];
        b = ai;
        Arrays.fill(ai, -1);
        int i = a.length;
        for(int j = 0; j < i; j++)
            b[a[j]] = j;

        b[61] = 0;
    }     
}
