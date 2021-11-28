package layout;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.util.ResourceBundle;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import util.JDBCUtil;
import util.TherdUtil;

public class MainController implements Initializable {

	MediaPlayer mp; // 음악재생 라이브러리
	Media m = null; // 음악 소스

	// false: 음악 나오는 중
	public boolean mediaMute = false;

	@FXML
	private Button StartBtn;
	@FXML
	private Button ChangeJoinBtn;
	@FXML
	private Button ChangeLoginBtn;
	@FXML
	private Button joinBtn;
	@FXML
	private Button loginBtn;
	@FXML
	private Button loadingBtn;
	@FXML
	private Button NewGame;
	@FXML
	private Button EndProgram;
	@FXML
	private Button EndSave;
	@FXML
	private Button commentH;
	@FXML
	private Button commentG;
	@FXML
	private Button commentI;
	@FXML
	private Button commentK;
	@FXML
	private Button LoadGame;
	@FXML
	private Button Setting;
	@FXML
	private Button EndGame;
	@FXML
	private Button SubmitBtn;
	@FXML
	private Button chgClear;
	@FXML
	private TextField userId;
	@FXML
	private PasswordField userPw;
	@FXML
	private TextField joinId;
	@FXML
	private PasswordField joinPw;
	@FXML
	private MediaView mediaView;
	@FXML
	private ImageView imageView;
	@FXML
	private Button buttonPlay;

	private boolean booEnd;

	@FXML
	private Button SaveBtn;
	@FXML
	private Label userIDDate;
	@FXML
	private Label userObjDate;
	@FXML
	private Label userBossDate;

	@FXML
	private Button ReStartBtn;
	@FXML
	private Button ReLoadBtn;
	@FXML
	private Button LifeEndBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void EndLife() {
		try {
			m = new Media(getClass().getResource("/resourse/Doki.mp3").toString());
			mp = new MediaPlayer(m);
			Runnable onEnd = new Runnable() {
				public void run() {
					mp.dispose();
					mp = new MediaPlayer(m);
					mp.play();
					mp.setOnEndOfMedia(this);
				}
			};
			mp.setOnEndOfMedia(onEnd);
			mp.play();

			Parent login = FXMLLoader.load(getClass().getResource("/layout/Index3.fxml"));
			Scene scene = new Scene(login);
			Stage primaryStage = (Stage) LifeEndBtn.getScene().getWindow();
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ReLoad() {
		try {
			Parent login = FXMLLoader.load(getClass().getResource("/layout/LoadGame.fxml"));
			Scene scene = new Scene(login);
			Stage primaryStage = (Stage) ReLoadBtn.getScene().getWindow();
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ReStart() {
		try {
			Parent login = FXMLLoader.load(getClass().getResource("/layout/StartGame/Start_Narration.fxml"));
			Scene scene = new Scene(login);
			Stage primaryStage = (Stage) ReStartBtn.getScene().getWindow();
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Start -> Login -> (로딩) -> Index
	// 화면 전환
	public void ChangeJoin() {
		try {
			Parent login = FXMLLoader.load(getClass().getResource("/layout/Join.fxml"));
			Scene scene = new Scene(login);
			Stage primaryStage = (Stage) ChangeJoinBtn.getScene().getWindow();
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ChangeLogin() {
		try {
			Parent login = FXMLLoader.load(getClass().getResource("/layout/Login.fxml"));
			Scene scene = new Scene(login);
			Stage primaryStage = (Stage) ChangeLoginBtn.getScene().getWindow();
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Start 화면에서 Login 화면으로 이동
	public void StartChangeLogin() {
		try {
			Parent login = FXMLLoader.load(getClass().getResource("/layout/Login.fxml"));
			Scene scene = new Scene(login);
			Stage primaryStage = (Stage) StartBtn.getScene().getWindow();
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);

//			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("/resourse/Cursor3.ogg"));
//			Clip clip = AudioSystem.getClip();
//			clip.stop();
//			clip.open(ais);
//			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// EndGame 누르면 Start 화면으로 이동
	public void ChangeStart() {
		try {
			Parent login = FXMLLoader.load(getClass().getResource("/layout/Start.fxml"));
			Scene scene = new Scene(login);
			Stage primaryStage = (Stage) EndGame.getScene().getWindow();
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);

//			// Index에서 나오던 음악 음소거
//			mp.setMute(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// setting 누르면 setting 화면으로 이동
	public void ChangeSetting() {
		try {
			Parent login = FXMLLoader.load(getClass().getResource("/layout/Setting.fxml"));
			Scene scene = new Scene(login);
			Stage primaryStage = (Stage) Setting.getScene().getWindow();
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 코멘트
	public void commentH() {
		try {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("개발자 코멘트");
			alert.setHeaderText("10103 박하영");
			alert.setContentText("세상에... 피곤하다 아직도 목요일이라니");
			alert.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void commentG() {
		try {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("개발자 코멘트");
			alert.setHeaderText("10111 김건우");
			alert.setContentText("개발중단");
			alert.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void commentKH() {
		try {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("개발자 코멘트");
			alert.setHeaderText("10115 김환");
			alert.setContentText("코멘트를 입력하세요");
			alert.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void commentY() {
		try {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("개발자 코멘트");
			alert.setHeaderText("10105 임수연");
			alert.setContentText("불친절이 컨셉인 게임");
			alert.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 로그인
	public String userN;
	public String AuserID;

	public void loginCheck() {
		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String loginId = userId.getText();
		String loginPw = userPw.getText();
		Alert alert = new Alert(AlertType.WARNING);

		if (loginId.isEmpty() && loginPw.isEmpty()) {
			alert.setTitle("빈 값이 있습니다!");
			alert.setHeaderText("Warning Dialog");
			alert.setContentText("아이디와 비밀번호 입력에 오류가 있습니다!");
			alert.show();
		}

		String sql = "SELECT `id`, `password` FROM `user` WHERE `id` = ? AND `password` = ?";
//		SELECT u.id, u.password, d.avatar_id, o.object_id FROM user u, data d, user_object o  WHERE u.id = 12 AND u.password = 1234 AND u.id = o.user_id LIKE o.id = '%12%'

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginId);
			pstmt.setString(2, loginPw);
			ResultSet rs = null;
			rs = pstmt.executeQuery();
			if (rs.next()) {
				try {
					Parent login = FXMLLoader.load(getClass().getResource("/layout/Index.fxml"));
					Scene scene = new Scene(login);
					Stage primaryStage = (Stage) loginBtn.getScene().getWindow();
					scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
					primaryStage.setScene(scene);

					// 쓰레드에 로그인 정보 담기
					// Thread t = new Thread(new TherdUtil(loginId));
					// t.start();

					m = new Media(getClass().getResource("/resourse/Index.mp3").toString());
					mp = new MediaPlayer(m);
					Runnable onEnd = new Runnable() {
						public void run() {
							mp.dispose();
							mp = new MediaPlayer(m);
							mp.play();
							mp.setOnEndOfMedia(this);
						}
					};
					mp.setOnEndOfMedia(onEnd);
					mp.play();

//					소리 너무 큼!!

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				alert.setTitle("오류가 있습니다!");
				alert.setHeaderText("Warning Dialog");
				alert.setContentText("아이디와 비밀번호 입력에 오류가 있습니다!");
				alert.show();
			}
		} catch (Exception e) {
			e.printStackTrace();
			alert.setTitle("오류가 있습니다!");
			alert.setHeaderText("Warning Dialog");
			alert.setContentText("아이디와 비밀번호 입력에 오류가 있습니다!");
			alert.show();
		}
	}

	// 회원가입
	public void join() {
		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String inputJoinId = joinId.getText();
		String inputJoinPw = joinPw.getText();
		Alert alert = new Alert(AlertType.WARNING);

		if (inputJoinId.isEmpty() && inputJoinPw.isEmpty()) {
			alert.setTitle("빈 값이 있습니다!");
			alert.setHeaderText("Warning Dialog");
			alert.setContentText("입력에 오류가 있습니다!");
			alert.show();
		}

		String sql = "INSERT INTO `user`(`id`, `password`) VALUES (?,?)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, inputJoinId);
			pstmt.setString(2, inputJoinPw);
			pstmt.executeUpdate();
			try {
				db = new JDBCUtil();
				con = db.getConnection();
				pstmt = null;
				sql = "INSERT INTO `data`(`id`, `user_id`, `date`, `last_map`) VALUES ?, ?, ?";
				try {
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, userId.getText());
					pstmt.setString(2, userId.getText());
					pstmt.setString(3, "S-01");
					pstmt.execute();

				} catch (Exception e) {
					// TODO: handle exception
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				Parent login = FXMLLoader.load(getClass().getResource("/layout/Login.fxml"));
				Scene scene = new Scene(login);
				Stage primaryStage = (Stage) joinBtn.getScene().getWindow();
				scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
				primaryStage.setScene(scene);
			} catch (Exception e) {
				e.printStackTrace();
				alert.setTitle("오류가 있습니다!");
				alert.setHeaderText("Warning Dialog");
				alert.setContentText("입력에 오류가 있습니다!");
				alert.show();
			}
		} catch (Exception e) {
			e.printStackTrace();
			alert.setTitle("오류가 있습니다!");
			alert.setHeaderText("Warning Dialog");
			alert.setContentText("입력에 오류가 있습니다!");
			alert.show();
		}
	}

	// 창 닫기
	public void closeProgram() { // 현재의 스테이지를 받아서 close를 해주어야 함
		Stage pop = (Stage) EndProgram.getScene().getWindow(); // 버튼을 통해서 현재 스테이지를 알아냄
		pop.close();

//		// Index에서 나오던 음악 음소거
//		mp.setMute(true);
	}

	public void EndSave() {
		try {
			Parent login = FXMLLoader.load(getClass().getResource("/layout/map/BasicMap.fxml"));
			Scene scene = new Scene(login);
			Stage primaryStage = (Stage) EndSave.getScene().getWindow();
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//		게임 시작 (새 게임 버튼 눌렀을 때부터)
	public void StartGame() {
		try {
			Parent login = FXMLLoader.load(getClass().getResource("/layout/StartGame/Start_Narration.fxml"));
			Scene scene = new Scene(login);
			Stage primaryStage = (Stage) NewGame.getScene().getWindow();
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);

			// Index에서 나오던 음악 음소거
			mediaMute = true;
			if (mediaMute == true) {
				mp.setMute(mediaMute);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 게임 불러오기 눌렀을 때
	public void LoadGame() {
		try {
			Parent login = FXMLLoader.load(getClass().getResource("/layout/LoadGame.fxml"));
			Scene scene = new Scene(login);
			Stage primaryStage = (Stage) LoadGame.getScene().getWindow();
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void SubChg() {
		try {
			Parent login = FXMLLoader.load(getClass().getResource("/layout/Clear.fxml"));
			Scene scene = new Scene(login);
			Stage primaryStage = (Stage) SubmitBtn.getScene().getWindow();
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clearChg() {
		try {
			Parent login = FXMLLoader.load(getClass().getResource("/layout/Clear2.fxml"));
			Scene scene = new Scene(login);
			Stage primaryStage = (Stage) chgClear.getScene().getWindow();
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
