import java.util.Scanner;

import model.Facade;
import model.ProductDAO;
import model.ProductModel;
import model.UserDAO;
import model.UserModel;

public class Main {

	private Facade fc = new Facade();
	
	public static void main(String[] args) {
		new Main();
	}
	Main(){
		while (true) {
			showMainMenu();
			int op = new Scanner(System.in).nextInt();
			
			if (op == 0)
				System.exit(0);
			if (op == 1) {
				if(registerUser())
					System.out.println("Salvo com sucesso!\n\n");
				else
					System.out.println("Falha ao salvar!\n\n");
					
			}else if(op == 2)
				if(deleteUser())
					System.out.println("Excluido com sucesso!");
				else
					System.out.println("Erro ao tentar remover. :(");
			
			else if(op == 3)
				if(editUser())
					System.out.println("Editado com sucesso!");
				else
					System.out.println("Erro ao tentar editar. :(");

			else if (op == 4) {
				if(registerProduct())
					System.out.println("Salvo com sucesso!\n\n");
				else
					System.out.println("Falha ao salvar!\n\n");
					
			}
			else if(op == 5)
				if(deleteProduct())
					System.out.println("Excluido com sucesso!");
				else
					System.out.println("Erro ao tentar remover. :(");
			
			else if(op == 6)
				if(editProduct())
					System.out.println("Editado com sucesso!");
				else
					System.out.println("Erro ao tentar editar. :(");

		}
		
	}
	private boolean registerProduct() {
		System.out.print("Digite o codigo de barras do produto: ");
		String id = new Scanner(System.in).next();
		
		System.out.print("Digite o nome do produto:");
		String name = new Scanner(System.in).next();
		
		System.out.print("Digite a descrição: ");
		String desc = new Scanner(System.in).next();
		
		System.out.print("Digite preço: ");
		float price = new Scanner(System.in).nextFloat();
		
		ProductModel p = new ProductModel();;
		
		p.setId(id);
		p.setName(name);
		p.setPrice(price);
		p.setDescricao(desc);
		
		return new ProductDAO(p).save();
		
	}
	private boolean deleteUser() {
		System.out.print("Digite o CPF do usuario a ser deletado: ");
		String id = new Scanner(System.in).next();
		
		return new UserDAO(null).delete(id);
	}
	private boolean deleteProduct() {
		System.out.print("Digite o cod de barras do produto a ser deletado: ");
		String id = new Scanner(System.in).next();
		
		return new ProductDAO(null).delete(id);
	}
private boolean editUser() {
		
		System.out.print("Digite o CPF do usuario que deseja alterar: ");
		String cpf = new Scanner(System.in).next();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Digite o novo nome do usuario: ");
		String name = sc.nextLine();
		
		System.out.print("Digite o novo tipo de usuario (Admin, user): ");
		String type = sc.nextLine();
		
		System.out.print("Digite a nova senha do usuario: ");
		String pass = sc.nextLine();
		
		UserModel u = new UserDAO(null).getUser(cpf);
		UserDAO dao = new UserDAO(u);
		
		if(u == null)
			return false;
		return dao.edit(name, pass, type);
	}
	private boolean editProduct() {
		
		System.out.print("Digite o cod. de barra do produto: ");
		String id = new Scanner(System.in).next();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Digite o novo nome do produto: ");
		String name = sc.nextLine();
		
		System.out.print("Digite a nova descrição: ");
		String desc = sc.nextLine();
		
		System.out.print("Digite o novo preço: ");
		float price = sc.nextFloat();
		
		
		ProductModel p = new ProductDAO(null).getProduct(id);
		ProductDAO dao = new ProductDAO(p);
		
		if(p == null)
			return false;
		return false;//dao.edit(name, desc, price);
	}
	private boolean registerUser() {
//		System.out.print("Digite o CPF do usuario: ");
//		String cpf = new Scanner(System.in).next();
//		
//		Scanner sc = new Scanner(System.in);
//		
//		System.out.print("Digite o nome do usuario: ");
//		String name = sc.nextLine();
//		
//		System.out.print("Digite o tipo de usuario (Admin, user): ");
//		String type = sc.nextLine();
//		
//		System.out.print("Digite a senha do usuario: ");
//		String pass = sc.nextLine();
//		
//		try {
//			return fc.saveUser();
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			return false;
//		}
		return false;
	}
	void showMainMenu() {
		System.out.print(
				  "1 - Cadastrar usuario.\n"
				+ "2 - Excluir usuario.\n"
				+ "3 - Editar usuario.\n"
				+ "4 - Cadastrar produto.\n"
				+ "5 - Excluir produto.\n"
				+ "6 - Editar produto.\n\n"
				+ "0 - Sair\n\nOpção: ");
	}
}
