package com.czy.weather.Util;

public interface HttpCallbackListener {
	// �ɹ�ʱ�ص�
	void onFinish(String response);

	// ����ʱ�ص�
	void onError(Exception e);

}
