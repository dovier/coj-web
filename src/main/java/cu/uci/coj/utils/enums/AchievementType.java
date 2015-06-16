/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.uci.coj.utils.enums;

//el orden corresponde con los ids en la base de datos, favor no reorganizar este enum ni cambiar los ids de la tabla achievement manualmente
public enum AchievementType {

	CSHARP_MASTER {
		@Override
		public int[] levels() {
			return LEVELS;
		}

		@Override
		public int id() {
			return 1;
		}

	},
	JAVA_MASTER {
		@Override
		public int[] levels() {
			return LEVELS;
		}

		@Override
		public int id() {
			return 2;
		}

	},
	CPLUSPLUS_MASTER {
		@Override
		public int[] levels() {
			return LEVELS;
		}

		@Override
		public int id() {
			return 3;
		}

	},
	CPLUSPLUS11_MASTER {
		@Override
		public int[] levels() {
			return LEVELS;
		}

		@Override
		public int id() {
			return 13;
		}

	},
	HASKELL_MASTER {
		@Override
		public int[] levels() {
			return LEVELS;
		}

		@Override
		public int id() {
			return 14;
		}

	},
	C_MASTER {
		@Override
		public int[] levels() {
			return LEVELS;
		}

		@Override
		public int id() {
			return 4;
		}

	},
	SNIPER {
		public int[] SNIPER_LEVELS = new int[] { 0, 5, 10, 20, 50 };

		@Override
		public int[] levels() {
			return SNIPER_LEVELS;
		}

		@Override
		public int id() {
			return 5;
		}
	},
	RUBY_MASTER {
		@Override
		public int[] levels() {
			return LEVELS;
		}

		@Override
		public int id() {
			return 6;
		}

	},
	BASH_MASTER {
		@Override
		public int[] levels() {
			return LEVELS;
		}

		@Override
		public int id() {
			return 7;
		}

	},
	PHP_MASTER {
		@Override
		public int[] levels() {
			return LEVELS;
		}

		@Override
		public int id() {
			return 8;
		}

	},
	PYTHON_MASTER {
		@Override
		public int[] levels() {
			return LEVELS;
		}

		@Override
		public int id() {
			return 9;
		}

	},
	PERL_MASTER {

		@Override
		public int[] levels() {
			return LEVELS;
		}

		@Override
		public int id() {
			return 10;
		}

	},
	PASCAL_MASTER {

		@Override
		public int[] levels() {
			return LEVELS;
		}

		@Override
		public int id() {
			return 11;
		}

	},
	POLYGLOT {

		public int[] POLYGLOT_LEVELS = new int[] { 0, 2, 4, 5, 10 };

		@Override
		public int[] levels() {
			return POLYGLOT_LEVELS;
		}

		@Override
		public int id() {
			return 12;
		}

	},

	BRUTE_FORCE {

		@Override
		public int[] levels() {
			return LEVELS;
		}

		@Override
		public int id() {
			return 15;
		}

	},
	MATH_WIZ {

		@Override
		public int[] levels() {
			return LEVELS;
		}

		@Override
		public int id() {
			return 16;
		}

	},
	COMBINATION_MASTER {

		@Override
		public int[] levels() {
			return LEVELS;
		}

		@Override
		public int id() {
			return 17;
		}

	},
	DATA_STRUCTURER {

		@Override
		public int[] levels() {
			return LEVELS;
		}

		@Override
		public int id() {
			return 18;
		}

	},
	DYNAMIC {

		@Override
		public int[] levels() {
			return LEVELS;
		}

		@Override
		public int id() {
			return 19;
		}

	},
	GAME_THEORIST {

		@Override
		public int[] levels() {
			return LEVELS;
		}

		@Override
		public int id() {
			return 20;
		}

	},
	DRAWING_MASTER {

		@Override
		public int[] levels() {
			return LEVELS;
		}

		@Override
		public int id() {
			return 21;
		}

	},
	GREEDY_GECKO {

		@Override
		public int[] levels() {
			return LEVELS;
		}

		@Override
		public int id() {
			return 22;
		}

	},
	NUMBER_THEORIST {

		@Override
		public int[] levels() {
			return LEVELS;
		}

		@Override
		public int id() {
			return 23;
		}

	},
	SEARCHER {

		@Override
		public int[] levels() {
			return LEVELS;
		}

		@Override
		public int id() {
			return 24;
		}

	},
	GRAPH_THEORIST {

		@Override
		public int[] levels() {
			return LEVELS;
		}

		@Override
		public int id() {
			return 25;
		}

	},
	STRING_THEORIST {

		@Override
		public int[] levels() {
			return LEVELS;
		}

		@Override
		public int id() {
			return 26;
		}

	},
	ODDBALL {

		@Override
		public int[] levels() {
			return LEVELS;
		}

		@Override
		public int id() {
			return 27;
		}

	},
	PROLOG_MASTER {

		@Override
		public int[] levels() {
			return LEVELS;
		}

		@Override
		public int id() {
			return 28;
		}

	},
	INFLATE_THE_BALLOON {

		@Override
		public int[] levels() {
			return new int[] {UNASSIGNED,1,100,500,1000};
		}

		@Override
		public int id() {
			return 29;
		}

	},
	RHINO_JS_MASTER {

		@Override
		public int[] levels() {
			return LEVELS;
		}

		@Override
		public int id() {
			return 30;
		}

	},
	NODE_JS_MASTER {

		@Override
		public int[] levels() {
			return LEVELS;
		}

		@Override
		public int id() {
			return 31;
		}

	},
	VB_MASTER {

		@Override
		public int[] levels() {
			return LEVELS;
		}

		@Override
		public int id() {
			return 32;
		}

	};

	// la posicion es igual al id(), que es igual al id en la tabla achievement
	public static final AchievementType[] achievements = new AchievementType[] { null, CSHARP_MASTER, JAVA_MASTER, CPLUSPLUS_MASTER, C_MASTER, SNIPER, RUBY_MASTER, BASH_MASTER, PHP_MASTER,
			PYTHON_MASTER, PERL_MASTER, PASCAL_MASTER, POLYGLOT, CPLUSPLUS11_MASTER, HASKELL_MASTER, BRUTE_FORCE, MATH_WIZ, COMBINATION_MASTER, DATA_STRUCTURER, DYNAMIC, GAME_THEORIST,
			DRAWING_MASTER, GREEDY_GECKO, NUMBER_THEORIST, SEARCHER, GRAPH_THEORIST, STRING_THEORIST, ODDBALL,PROLOG_MASTER,INFLATE_THE_BALLOON,RHINO_JS_MASTER,NODE_JS_MASTER,VB_MASTER};
	public int UNASSIGNED = 0;
	public int BEGINNER = 10;
	public int BRONZE = 20;
	public int SILVER = 50;
	public int GOLD = 100;
	public int[] LEVELS = new int[] { UNASSIGNED, BEGINNER, BRONZE, SILVER, GOLD };
	public int[] LEVELSx10 = new int[] { UNASSIGNED*10, BEGINNER*10, BRONZE*10, SILVER*10, GOLD*10 };

	public int[] levels() {
		return LEVELS;
	}

	public abstract int id();

	public int progress(int count) {
		int min = currentLevel(count);
		int max = nextLevel(count);

		if (levels()[max] == levels()[min])
			return 0;
		return (count - levels()[min]) * 100 / (levels()[max] - levels()[min]);

	}

	public int currentLevel(int count) {
		int[] levels = levels();
		int i = levels.length - 1;
		while (count < levels[i] && i >= 0)
			i--;
		return i;

	}

	public int nextLevel(int count) {
		return Math.min(currentLevel(count) + 1, levels().length-1);
	}
}
