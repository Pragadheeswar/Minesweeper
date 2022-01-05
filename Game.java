import java.util.Scanner;

class Area{
	int row,column,bomb=35;
	boolean game = true;
	char[][] backField,playField;
	Area(int row,int column){
		this.row=row;
		this.column=column;
		backField = new char[row][column];
		for(int i=0;i<row;i++)
			for(int j=0;j<column;j++)
				backField[i][j]='X';
		playField = new char[row][column];
		this.copy();
		this.setBomb();
		this.setNumber();
	}
	
	void copy() {
		for(int i=0;i<row;i++)
			for(int j=0;j<column;j++)
				playField[i][j]='X';
	}
	
	void setBomb() {
		int r,c;
		for(int i=0;i<bomb;i++) {
			r =(int)(Math.random()*(this.row));
			c =(int)(Math.random()*(this.column));
			backField[r][c]='B';
		}
		
	}
	
	void show() {
		for(int i = 0;i<row;i++) {
			for(int j=0;j<column;j++)
				System.out.print(playField[i][j]+" ");
			System.out.println();
		}
	}
	
	void number(int a,int b) {
		int n=0;
		if(backField[a][b]=='B') {
			return;
		}
		for(int i=-1;i<=1;i++)
			for(int j=-1;j<=1;j++){
				try {
					if(backField[a+i][b+j]=='B')
						n++;
			}
				catch(Exception e) {}
			}
		if(n==0) {
			backField[a][b]='-';
		}
		else {
		backField[a][b]=(char)(n+'0');
		}
	}
	
	void setNumber() {
		for(int i = 0;i<row;i++) {
			for(int j=0;j<column;j++)
				number(i,j);
	}
	}
	
	
	void round(int a,int b) {
		try {
			if(playField[a][b]=='X') {
				if(backField[a][b]=='B') {
					return;
				}
				else if(backField[a][b]!='-') {
					playField[a][b]=backField[a][b];
					return;
				}
				else {
					playField[a][b]=backField[a][b];
					for(int i=-1;i<=1;i++)
						for(int j=-1;j<=1;j++)
							if(i!=0 || j!=0)
								round(a+i,b+j);

				}
			}
		}
		catch(Exception e) {}
	}
	
	void click(int a,int b) {
			if(backField[a][b]=='B'){
				playField[a][b]='B';
				game = false;
				return;
			}
			else if(backField[a][b]!='-'){
				playField[a][b]=backField[a][b];
			}
			else {
				
				round(a,b);
			}
	}
	
	void flag(int a,int b) {
		playField[a][b]='F';
	}

}




public class Game {
	public static void main(String[] args) {
		Scanner ch = new Scanner(System.in);
		Area a1 = new Area(20,10);
		a1.show();
		while(a1.game) {
			System.out.println("1.Click\n2.Flag");
			int c = ch.nextInt();
			System.out.print("Enter Location:");
			if(c==1) {
				int a=ch.nextInt();
				int b = ch.nextInt();
				a1.click(a, b);
			}
			else if(c==2){
				int a=ch.nextInt();
				int b = ch.nextInt();
				a1.flag(a,b);
			}
			a1.show();
		}
		System.out.println("Game over");
		
	}
}