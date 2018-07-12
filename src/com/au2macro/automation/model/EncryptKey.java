package com.au2macro.automation.model;

import java.util.Arrays;

public class EncryptKey {
	private byte[] keyCipher;
	private String keyText;
	public byte[] getKeyCipher() {
		return keyCipher;
	}
	public void setKeyCipher(byte[] keyCipher) {
		this.keyCipher = keyCipher;
	}
	public String getKeyText() {
		return keyText;
	}
	public void setKeyText(String keyText) {
		this.keyText = keyText;
	}
	@Override
	public String toString() {
		return "Key [keyCipher=" + Arrays.toString(keyCipher) + ", keyText=" + keyText + "]";
	}
	
}
