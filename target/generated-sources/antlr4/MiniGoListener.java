// Generated from MiniGo.g4 by ANTLR 4.4
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MiniGoParser}.
 */
public interface MiniGoListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MiniGoParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDecl(@NotNull MiniGoParser.DeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniGoParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDecl(@NotNull MiniGoParser.DeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniGoParser#fun_decl}.
	 * @param ctx the parse tree
	 */
	void enterFun_decl(@NotNull MiniGoParser.Fun_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniGoParser#fun_decl}.
	 * @param ctx the parse tree
	 */
	void exitFun_decl(@NotNull MiniGoParser.Fun_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniGoParser#if_stmt}.
	 * @param ctx the parse tree
	 */
	void enterIf_stmt(@NotNull MiniGoParser.If_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniGoParser#if_stmt}.
	 * @param ctx the parse tree
	 */
	void exitIf_stmt(@NotNull MiniGoParser.If_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniGoParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(@NotNull MiniGoParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniGoParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(@NotNull MiniGoParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniGoParser#params}.
	 * @param ctx the parse tree
	 */
	void enterParams(@NotNull MiniGoParser.ParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniGoParser#params}.
	 * @param ctx the parse tree
	 */
	void exitParams(@NotNull MiniGoParser.ParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniGoParser#expr_stmt}.
	 * @param ctx the parse tree
	 */
	void enterExpr_stmt(@NotNull MiniGoParser.Expr_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniGoParser#expr_stmt}.
	 * @param ctx the parse tree
	 */
	void exitExpr_stmt(@NotNull MiniGoParser.Expr_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniGoParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void enterCompound_stmt(@NotNull MiniGoParser.Compound_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniGoParser#compound_stmt}.
	 * @param ctx the parse tree
	 */
	void exitCompound_stmt(@NotNull MiniGoParser.Compound_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniGoParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(@NotNull MiniGoParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniGoParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(@NotNull MiniGoParser.ArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniGoParser#local_decl}.
	 * @param ctx the parse tree
	 */
	void enterLocal_decl(@NotNull MiniGoParser.Local_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniGoParser#local_decl}.
	 * @param ctx the parse tree
	 */
	void exitLocal_decl(@NotNull MiniGoParser.Local_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniGoParser#type_spec}.
	 * @param ctx the parse tree
	 */
	void enterType_spec(@NotNull MiniGoParser.Type_specContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniGoParser#type_spec}.
	 * @param ctx the parse tree
	 */
	void exitType_spec(@NotNull MiniGoParser.Type_specContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniGoParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(@NotNull MiniGoParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniGoParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(@NotNull MiniGoParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniGoParser#for_stmt}.
	 * @param ctx the parse tree
	 */
	void enterFor_stmt(@NotNull MiniGoParser.For_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniGoParser#for_stmt}.
	 * @param ctx the parse tree
	 */
	void exitFor_stmt(@NotNull MiniGoParser.For_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniGoParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(@NotNull MiniGoParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniGoParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(@NotNull MiniGoParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniGoParser#var_decl}.
	 * @param ctx the parse tree
	 */
	void enterVar_decl(@NotNull MiniGoParser.Var_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniGoParser#var_decl}.
	 * @param ctx the parse tree
	 */
	void exitVar_decl(@NotNull MiniGoParser.Var_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniGoParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(@NotNull MiniGoParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniGoParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(@NotNull MiniGoParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniGoParser#assign_stmt}.
	 * @param ctx the parse tree
	 */
	void enterAssign_stmt(@NotNull MiniGoParser.Assign_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniGoParser#assign_stmt}.
	 * @param ctx the parse tree
	 */
	void exitAssign_stmt(@NotNull MiniGoParser.Assign_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniGoParser#return_stmt}.
	 * @param ctx the parse tree
	 */
	void enterReturn_stmt(@NotNull MiniGoParser.Return_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniGoParser#return_stmt}.
	 * @param ctx the parse tree
	 */
	void exitReturn_stmt(@NotNull MiniGoParser.Return_stmtContext ctx);
}