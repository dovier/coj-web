/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.model;

public class ProblemSource implements Comparable<Object> {

    private String name;
    private String author;
    private int idSource;

    public ProblemSource(String name, String author,int idSource) {
        this.name = name;
        this.author = author;
        this.idSource = idSource;

    }


    public ProblemSource() {
    }
    
    public String getFullName(){
    	
    	return (name==null?"":name) + (author==null?"":" [" + author + "]");
    }

    public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdSource() {
		return idSource;
	}


	public void setIdSource(int idSource) {
		this.idSource = idSource;
	}

    public int compareTo(Object o) {
        return idSource - ((ProblemSource) o).idSource;
    }

	@Override
    public String toString(){
        return this.name;
    }

}