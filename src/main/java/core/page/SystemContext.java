package core.page;

/**
 * 分页
 *
 * @author chenweju
 *
 * @since Apr 24, 2015
 */
public class SystemContext {

	private static ThreadLocal<Integer> offset = new ThreadLocal<Integer>();
	private static ThreadLocal<Integer> pagesize = new ThreadLocal<Integer>();
	
	public static int getOffset(){
		Integer os = (Integer)offset.get();
		if(os == null){
			return 1;
		}
		return os;
	}
	
	public static void setOffset(int offsetvalue){
		offset.set(offsetvalue);
	}
	
	public static void removeOffset(){
		offset.remove();
	}
	
	public static int getPagesize(){
		Integer ps = (Integer)pagesize.get();
		if(ps == null){
			return 10;
		}
		return ps;
	}
	
	public static void setPagesize(int pagesizevalue){
		pagesize.set(pagesizevalue);
	}
	
	public static void removePagesize(){
		pagesize.remove();
	}
	
}
