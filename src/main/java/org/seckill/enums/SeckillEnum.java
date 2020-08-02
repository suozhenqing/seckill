package org.seckill.enums;
/*
 * ʹ��ö�ٱ�ʾ��������
 */
public enum SeckillEnum {
	SUCCESS(1,"��ɱ�ɹ�"),
	END(0,"��ɱ����"),
	REPEAT_KILL(-1,"�ظ���ɱ"),
	INNER_ERROR(-2,"ϵͳ�쳣"),
	DATA_REWRITE(-3,"���ݴ۸�");
	
	private int state;
	
	private String stateInfo;

	private SeckillEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}
	
	public static SeckillEnum stateOf(int index){
		for (SeckillEnum state : values()) {
			if(state.getState() == index){
				return state;
			}
		}
		return null;
	}
}
