package exam03;

import java.util.List;
import java.util.Scanner;

public class ConnPoolTest {

	public static void select2(ConnPoolDAO con) {
		
		Scanner keyboard = new Scanner(System.in);
		ConnPoolDTO dto = new ConnPoolDTO();
		System.out.println("customer 테이블에 값 검색하기.");
		System.out.print("검색할 번호를 입력하세요: ");
		int no = keyboard.nextInt();
		

		dto.setNo(no);
		con.selectDao2(dto);
		
		System.out.printf("%d\t%s\t%s\t%s\n",dto.getNo(),dto.getName(),dto.getEmail(),dto.getTel());
	}
	
	public static void delete(ConnPoolDAO con) {
		Scanner sc = new Scanner(System.in);
		ConnPoolDTO dto = new ConnPoolDTO();
		
		System.out.println("테이블 값 삭제하기");
		System.out.print("삭제할 번호를 입력하세요:");
		int no = sc.nextInt();
		
		dto.setNo(no);
		
		con.deleteDAO(dto);
		
	}
	
	public static void update(ConnPoolDAO con) {
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("테이블 값 수정하기");
		System.out.print("수정할 번호를 입력하세요:");
		int no = sc.nextInt();
		sc.nextLine(); 
		System.out.print("수정할 이름을 입력하세요: ");
		String name = sc.nextLine();
		System.out.print("수정할 이메일을 입력하세요: ");
		String email = sc.nextLine();
		System.out.print("수정할 전화번호을 입력하세요: ");
		String tel = sc.nextLine();
		
		ConnPoolDTO dto = new ConnPoolDTO();
		dto.setNo(no);
		dto.setName(name);
		dto.setEmail(email);
		dto.setTel(tel);
		
		con.updateDAO(dto);
	}
	
	
	public static void select(ConnPoolDAO con) {

		List<ConnPoolDTO> list =con.selectDao();
		
		System.out.println("번호\t이름\t이메일\t전화번호");
		System.out.println("------------------------------");
		
		// ConnPoolDTO 특징
		// 자바빈
		// 셋팅하고싶으면 세터세터세터
		// 값을 얻어오고싶으면 게터게터게터
		
		for(ConnPoolDTO dto : list) {
			System.out.printf("%d\t%s\t%s\t%s\n",dto.getNo(),dto.getName(),dto.getEmail(),dto.getTel());
		}
	}

	// ConnPoolDAO con > 주소값을 반환
	public static void insert(ConnPoolDAO con) {
		Scanner keyboard = new Scanner(System.in);

		System.out.println("customer 테이블에 값 입력하기.");
		System.out.print("저장할 번호를 입력하세요: ");
		String no = keyboard.nextLine();

		System.out.print("이름을 입력하세요: ");
		String name = keyboard.nextLine();

		System.out.print("이메일 입력하세요: ");
		String email = keyboard.nextLine();

		System.out.print("전화번호를 입력하세요: ");
		String tel = keyboard.nextLine();

		int intNo = Integer.parseInt(no);

		ConnPoolDTO dto = new ConnPoolDTO();
		dto.setNo(intNo);
		dto.setName(name);
		dto.setEmail(email);
		dto.setTel(tel);

		con.insertDao(dto);

	}

	public static void main(String[] args) {

		ConnPoolDAO dao = new ConnPoolDAO();

		Scanner input = new Scanner(System.in);

		while (true) {
			System.out.println("\n\n*** 메뉴선택 ***");
			System.out.println("1. 프로그램 종료");
			System.out.println("2. 데이터 저장하기");
			System.out.println("3. 데이터 불러오기");
			System.out.println("4. 데이터 수정하기");
			System.out.println("5. 데이터 삭제하기");
			System.out.println("6. 데이터 검색하기");
			System.out.print(">>메뉴선택: ");

			int choice = input.nextInt();

			switch (choice) {
			case 1:
				System.out.println("프로그램을 종료합니다.");
				return;
			case 2:
				insert(dao);
				break;
			case 3:
				select(dao);
				break;
			case 4:
				update(dao);
				break;
			case 5:
				delete(dao);
				break;
			case 6 :
				select2(dao);
			default:
				System.out.println("메뉴번호를 확인하세요");
				break;
			}
		}
	}
}
