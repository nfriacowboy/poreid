/*
 * The MIT License
 *
 * Copyright 2014 Rui Martinho (rmartinho@gmail.com), António Braz (antoniocbraz@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.poreid;

/**
 *
 * @author POReID
 */
public enum RSAPaddingSchemes {

    /**
     * Alias para padding ISO9796-2
     */
    //ISO9796_2("ISO9796-2"),

    /**
     * Alias para padding RFC2409
     */
    //RFC2409("RFC2409"),
    
    /**
     * Alias para padding PKCS#1
     */
    PKCS1("PKCS#1");
    
    
    private final String name;

    /**
     * Verifica se o padding scheme fornecido é suportado
     * @param s padding scheme
     * @return RSAPaddingScheme correspondente ou null
     */
    public static RSAPaddingSchemes contains(String s) {
        for (RSAPaddingSchemes schemes : values()) {
            if (schemes.getStandardName().equalsIgnoreCase(s)) {
                return schemes;
            }
        }
        return null;
    }
    
    private String getStandardName() {
        return this.name;
    }
    
    private RSAPaddingSchemes(String name) {
        this.name = name;
    }
}
