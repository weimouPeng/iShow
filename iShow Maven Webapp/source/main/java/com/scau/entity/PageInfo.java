package com.scau.entity;

public class PageInfo {
    private int totalrecord;//�ܹ���¼���Ҫ��
    private int pagesize;//ÿҳ��¼���Ҫ��
    private int totalpage;//�ܹ�ҳ��ͨ��totalrecord��pagesize����
    private int currentpage;//��ǰҳ����Ҫ��
    private boolean hasPrePage; //�Ƿ�����һҳ
    private boolean hasNextPage;//�Ƿ�����һҳ
    private int beginIndex;//��ǰҳ���
    
    

    public PageInfo(int totalrecord, int pagesize, int currentpage) {
		super();
		this.totalrecord = totalrecord;
		this.pagesize = pagesize;
		this.currentpage = currentpage;
	}
    
    public int getTotalrecord() {
        return totalrecord;
    }
    public void setTotalrecord(int totalrecord) {
        this.totalrecord = totalrecord;
    }
    public int getPagesize() {
        return pagesize;
    }
    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }
    public int getTotalpage() {
        if(this.totalrecord%this.pagesize == 0){
            this.totalpage = this.totalrecord / this.pagesize;
        }else{
            this.totalpage = this.totalrecord / this.pagesize + 1;
        }
        return totalpage;
    }
    public int getCurrentpage() {
        return currentpage;
    }
    public void setCurrentpage(int currentpage) {
        this.currentpage = currentpage;
    }
    public boolean getHasPrePage() {
    	hasPrePage= this.currentpage == 1 ? false : true; 
    	return hasPrePage;
    }
    public boolean getHasNextPage() {
    	hasNextPage= this.currentpage == this.getTotalpage() || this.getTotalpage() == 0 ? false : true; 
    	return hasNextPage;
    }

	public int getBeginIndex() {
		beginIndex= (this.currentpage - 1) * this.pagesize;
		return beginIndex;
	}


}

