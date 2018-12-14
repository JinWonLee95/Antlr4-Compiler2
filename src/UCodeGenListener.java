import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.antlr.v4.runtime.tree.ParseTreeProperty;

public class UCodeGenListener extends MiniGoBaseListener {

	ParseTreeProperty<String> newTexts = new ParseTreeProperty<String>();
	ArrayList<saveInfo> infoList = new ArrayList<>();
	String forOutPut="";
	int blockNo = 1, offSet = 1, sizeGlo = 0, sizeLoc = 0;

	class saveInfo {
		String varName;
		int varSize;
		int base;
		int offset;

		public saveInfo(String name, int base, int offset, int size) {
			varName = name;
			this.base = base;
			this.offset = offset;
			varSize = size;
		}

	}


	@Override
	public void enterProgram(MiniGoParser.ProgramContext ctx) {
		System.out.println();
	}

	@Override
	public void exitProgram(MiniGoParser.ProgramContext ctx) {
		String str = "\n";

		for (int i = 0; i < ctx.getChildCount(); i++) {
			str += newTexts.get(ctx.decl(i).getChild(0));
		}
		newTexts.put(ctx, str);
		forOutPut += newTexts.get(ctx) + "\n           bgn\t"+sizeGlo+"\n           ldp\n           call main\n           end";
		System.out.println(forOutPut);
		
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter("result.txt"));
			bw.write(forOutPut);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void enterDecl(MiniGoParser.DeclContext ctx) {
		if (ctx.getChild(0) instanceof MiniGoParser.Var_declContext) {
			newTexts.put(ctx, newTexts.get(ctx.var_decl()));
		} else if (ctx.getChild(0) instanceof MiniGoParser.Fun_declContext) {
			newTexts.put(ctx, newTexts.get(ctx.fun_decl()));
		}
	}

	@Override
	public void enterVar_decl(MiniGoParser.Var_declContext ctx) {
		sizeGlo++;
		String varName = ctx.getChild(1).getText();
		int size = 0;

		if (ctx.getChildCount() == 3) {
			size = 1;
		} else if (ctx.getChildCount() == 5) {
			size = 1;
		} else if (ctx.getChildCount() == 6) {
			size = Integer.parseInt(ctx.getChild(3).getText());
		}

		infoList.add(new saveInfo(varName, blockNo, sizeGlo, size));
	}

	@Override
	public void exitVar_decl(MiniGoParser.Var_declContext ctx) {
		String varName = ctx.getChild(1).getText();
		saveInfo info = null;

		for (int i = 0; i < infoList.size(); i++) {
			if (varName.equals(infoList.get(i).varName)) {
				info = infoList.get(i);
			}
		}

		// System.out.println(" sym " + info.base + " " + info.offset + " " +
		// info.varSize);
		newTexts.put(ctx, "           sym\t" + info.base + "\t" + info.offset + "\t" + info.varSize+"\n");
	}

	@Override
	public void enterType_spec(MiniGoParser.Type_specContext ctx) {
		if (ctx.getChildCount() >= 1) {
			newTexts.put(ctx, ctx.getChild(0).getText());
		} else {
			newTexts.put(ctx, " ");
		}
	}

	@Override
	public void enterFun_decl(MiniGoParser.Fun_declContext ctx) {
		blockNo++;
	}

	@Override
	public void exitFun_decl(MiniGoParser.Fun_declContext ctx) {
		String funcName = ctx.getChild(1).getText();
		int space = 11 - funcName.length(); // 공백을 넣기 위한 변수

		while (space > 0) {
			funcName += " ";
			space--;
		}

		String value = funcName + "proc\t" + offSet + "\t" + blockNo + "\t2\n";
		value += newTexts.get(ctx.params());
		value += newTexts.get(ctx.compound_stmt());
		newTexts.put(ctx, value);
	}

	@Override
	public void exitParams(MiniGoParser.ParamsContext ctx) {
		if (ctx.getChild(0) instanceof MiniGoParser.ParamContext) {
			String s1 = newTexts.get(ctx.param(0));
			if (ctx.getChildCount() > 1) {
				for (int i = 1; i < ctx.getChildCount(); i++) {
					s1 += ", " + newTexts.get(ctx.getChild(i));
				}
			} else {
				s1 += ", " + newTexts.get(ctx.getChild(2));
			}
			newTexts.put(ctx, s1);
		} else {
			newTexts.put(ctx, "");
		}
	}

	@Override
	public void exitParam(MiniGoParser.ParamContext ctx) {
		String s1 = ctx.getChild(0).getText();

		if (ctx.getChildCount() != 1) {
			s1 += "[ ]" + newTexts.get(ctx.type_spec());
		} else {
			s1 += newTexts.get(ctx.type_spec());
		}
		newTexts.put(ctx, s1);
	}

	@Override
	public void exitStmt(MiniGoParser.StmtContext ctx) {
		if (ctx.getChild(0) instanceof MiniGoParser.Expr_stmtContext) {
			newTexts.put(ctx, newTexts.get(ctx.expr_stmt()));
		} else if (ctx.getChild(0) instanceof MiniGoParser.Compound_stmtContext) {
			newTexts.put(ctx, newTexts.get(ctx.compound_stmt()));
		} else if (ctx.getChild(0) instanceof MiniGoParser.Assign_stmtContext) {
			newTexts.put(ctx, newTexts.get(ctx.assign_stmt()));
		} else if (ctx.getChild(0) instanceof MiniGoParser.If_stmtContext) {
			newTexts.put(ctx, newTexts.get(ctx.if_stmt()));
		} else if (ctx.getChild(0) instanceof MiniGoParser.For_stmtContext) {
			newTexts.put(ctx, newTexts.get(ctx.for_stmt()));
		} else if (ctx.getChild(0) instanceof MiniGoParser.Return_stmtContext) {
			newTexts.put(ctx, newTexts.get(ctx.return_stmt()));
		}
	}

	@Override
	public void exitExpr_stmt(MiniGoParser.Expr_stmtContext ctx) {
		newTexts.put(ctx, newTexts.get(ctx.expr()));
	}


	@Override
	public void exitAssign_stmt(MiniGoParser.Assign_stmtContext ctx) {

		String name = ctx.getChild(0).getText();
		String lda = "           ldc\t" + newTexts.get(ctx.expr(0)) + "\n";

		for (int i = 0; i < infoList.size(); i++) {
			if (infoList.get(i).varName.equals(name)) {
				lda += "           lda\t" + infoList.get(i).base + "\t"+ infoList.get(i).offset
						+ "\n           add\n           ldc\t" + newTexts.get(ctx.getChild(5)).toString()
						+ "\n           sti";
			}
		}
		newTexts.put(ctx, lda);
	}

	@Override
	public void exitFor_stmt(MiniGoParser.For_stmtContext ctx) {
		newTexts.put(ctx,
				ctx.getChild(0).getText() + " " + newTexts.get(ctx.expr()) + " " + newTexts.get(ctx.compound_stmt()));
	}

	/*exit 으로 해야 결과 나옴*/
	@Override
	public void exitCompound_stmt(MiniGoParser.Compound_stmtContext ctx) {
		int ldCount = 0;
		int stmtCount = 0;
		String s1 = "";

		for (int i = 0; i < ctx.getChildCount(); i++) {
			if (ctx.getChild(i) instanceof MiniGoParser.Local_declContext) {
				ldCount++;
			} else if (ctx.getChild(i) instanceof MiniGoParser.StmtContext) {
				stmtCount++;
			}
		}

		for (int i = 0; i < ldCount; i++) {
			s1 += newTexts.get(ctx.local_decl(i)) + "\n";
		}
		for (int i = 0; i < stmtCount; i++) {
			s1 += newTexts.get(ctx.stmt(i));
		}
		newTexts.put(ctx, s1);
	}

	@Override
	public void enterLocal_decl(MiniGoParser.Local_declContext ctx) {
		int size=0;
		if (ctx.getChildCount() == 3) {
			size++;
		} else if (ctx.getChildCount() == 6) {
			size += Integer.parseInt(ctx.getChild(3).getText());
			
		}
		infoList.add(new saveInfo(ctx.getChild(1).getText(), blockNo, offSet, size));
		offSet += size;
	}

	@Override
	public void exitLocal_decl(MiniGoParser.Local_declContext ctx) {
		saveInfo si = null;
		String s1 = ctx.getChild(1).getText();
		
		for (int i = 0; i < infoList.size(); i++) {
			if (infoList.get(i).base == blockNo && infoList.get(i).varName.equals(s1)) {
				si = infoList.get(i);
			}
		}
		String a = "           sym\t" + si.base + "\t" + si.offset + "\t" + si.varSize;
		newTexts.put(ctx, a);
	}

	@Override
	public void exitIf_stmt(MiniGoParser.If_stmtContext ctx) {
		String s1 = ctx.getChild(0).getText() + " " + newTexts.get(ctx.expr()) + " "
				+ newTexts.get(ctx.compound_stmt(0));

		if (ctx.getChildCount() > 3) {
			s1 += ctx.getChild(3).getText() + " " + newTexts.get(ctx.compound_stmt(1));
		}

		newTexts.put(ctx, s1);
	}

	@Override
	public void exitReturn_stmt(MiniGoParser.Return_stmtContext ctx) {
		String s1 = ctx.getChild(0).getText() + " ";

		if (ctx.getChildCount() >= 2) {
			s1 += newTexts.get(ctx.expr(0));
		}

		if (ctx.getChildCount() > 2) {
			s1 += " , " + newTexts.get(ctx.expr(1));
		}

		newTexts.put(ctx, s1);
	}

	@Override
	public void exitExpr(MiniGoParser.ExprContext ctx) {
		String s1 = null, s2 = null, op = null;

		if (ctx.getChildCount() == 1) {
			newTexts.put(ctx, ctx.getChild(0).getText());
		}

		if (ctx.getChildCount() == 2) {
			newTexts.put(ctx, ctx.getChild(0).getText() + newTexts.get(ctx.expr(0)));
		}

		if (ctx.getChildCount() == 3) {
			if (ctx.getChild(0).getText().equals("(")) {
				newTexts.put(ctx, "(" + newTexts.get(ctx.expr(0)) + ")");
			} else if (ctx.getChild(1).getText().equals("=")) {
				newTexts.put(ctx, ctx.getChild(0).getText() + " = " + newTexts.get(ctx.expr(0)));
			} else if (ctx.getChild(1) != ctx.expr()) {
				// 예 : expr '+' expr
				s1 = newTexts.get(ctx.expr(0));
				s2 = newTexts.get(ctx.expr(1));
				op = ctx.getChild(1).getText();
				newTexts.put(ctx, s1 + " " + op + " " + s2);
			}
		}

		if (ctx.getChildCount() == 4) {
			if (ctx.getChild(1).getText().equals("[")) {
				newTexts.put(ctx, ctx.getChild(0).getText() + " [ " + newTexts.get(ctx.expr(0)) + " ] ");
			} else {
				newTexts.put(ctx, ctx.getChild(0).getText() + " (" + newTexts.get(ctx.args()) + ") ");
			}
		}

		if (ctx.getChildCount() == 6) {
			if (ctx.getChild(0) == ctx.FMT()) {
				newTexts.put(ctx, ctx.getChild(0).getText() + "." + ctx.getChild(2).getText() + " ("
						+ newTexts.get(ctx.args()) + ") ");
			} else {
//				newTexts.put(ctx, ctx.getChild(0).getText() + " [ " + newTexts.get(ctx.expr(0)) + " ] = "
//						+ newTexts.get(ctx.expr(1)));
				String name = ctx.getChild(0).getText();
				String lda = "           ldc\t" + newTexts.get(ctx.expr(0)) + "\n";

				for (int i = 0; i < infoList.size(); i++) {
					if (infoList.get(i).varName.equals(name)) {
						lda += "           lda\t" + infoList.get(i).base + "\t"+ infoList.get(i).offset
								+ "\n           add\n           ldc\t" + newTexts.get(ctx.getChild(5)).toString()
								+ "\n           sti";
					}
				}
				newTexts.put(ctx, lda);
			}
		}
	}

	@Override
	public void exitArgs(MiniGoParser.ArgsContext ctx) {
		String s1 = newTexts.get(ctx.expr(0));

		if (ctx.getChildCount() > 1) {
			for (int i = 1; i < ctx.getChildCount() - 1; i++) {
				s1 += " , " + newTexts.get(ctx.expr(i));
			}

		}
		newTexts.put(ctx, s1);
	}
}