package HajimeAPI4J.connect;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import HajimeAPI4J.api.HajimeAPI4J;
import HajimeAPI4J.exception.IllegalParameterException;
import HajimeAPI4J.exception.NoSuchParameterException;
import HajimeAPI4J.exception.NoSuchURIException;
import HajimeAPI4J.exception.ServerNotRespondError;
import HajimeAPI4J.responses.ListMusicResponse;
import HajimeAPI4J.responses.ResponseListProperty;



/**
 * ふじわらはじめAPIへ「/list?type=music」のリクエストを送信します。
 * @author Ranfa
 * @since 1.0.0
 *
 */
public class ListMusicRequest {

	// Declare field
	private static final String TYPE_PARAM = "music";
	private int limitParam;
	private String orderbyParam;
	private String orderParam;
	private String searchParam;
	private String[] musicTypesParam;
	private String requestURI;

	// Declare response
	private List<ListMusicResponse> responses = new ArrayList<>();

	// Declare logger
	private Logger logger = LoggerFactory.getLogger(ListMusicRequest.class);

	/**
	 * 全楽曲を取得する場合はこちらのコンストラクタを使用してください。
	 * APIとJavaの相互関係上、整数型で無限を表すことができないため、暫定的に用意しています。
	 */
	// Default constructor
	public ListMusicRequest() {
		logger.info("Constractor has been created with parameter: music");
		this.requestURI = String.format("%slist?type=%s", HajimeAPI4J.BASE_URI, TYPE_PARAM);
		this.limitParam = -1;
		this.orderbyParam = "";
		this.orderParam = "";
		this.searchParam = "";
		this.musicTypesParam = new String[]{null, null, null, null, null, null};
	}

	/**
	 * 詳細にパラメータを設定してコンストラクタを生成します。
	 * Javaでの引数順序の関係上、楽曲種類の指定は最後に記述してください。
	 * @param limitParam 出力する上限数
	 * @param orderbyParam 出力順序
	 * @param order 出力の昇順・降順
	 * @param search キーワード検索
	 * @param musicTypes 楽曲の種類
	 */
	// Detailed constructor
	public ListMusicRequest(int limitParam, String orderbyParam, String orderParam, String searchParam, String... musicTypesParam) {
		logger.info("Constructor has been created with parameter: {}, {}, {}, {}, {}", limitParam, orderbyParam, orderParam, searchParam, musicTypesParam);
		this.requestURI = String.format("%slist?type=%s&limit=%s&music_type=%s&orderby=%s&order=%s&search=%s", HajimeAPI4J.BASE_URI, TYPE_PARAM, limitParam, musicTypesParam, orderbyParam, orderParam, searchParam);
		this.limitParam = limitParam;
		this.orderbyParam = orderbyParam;
		this.orderParam = orderParam;
		this.searchParam = searchParam;
		this.musicTypesParam = musicTypesParam;
	}

	public List<ListMusicResponse> getResponse() throws IllegalParameterException, NoSuchParameterException, NoSuchURIException, ServerNotRespondError, IOException {
		request();
		return responses;
	}

	private void request() throws IllegalParameterException, NoSuchParameterException, NoSuchURIException, ServerNotRespondError, IOException {
		if(requestURI == null)
			throw new NoSuchURIException("URI seems to be null. This is bug. Please contact the developer with log file.");
		if((limitParam < -1) || (orderbyParam == null) || (orderParam == null) || (searchParam == null) || (musicTypesParam == null))
			throw new IllegalParameterException("Parameter seems to be invaild. This is bug. please contact the developer with log file.");
		ObjectMapper mapper = new ObjectMapper();
		ResponseListProperty property = new ResponseListProperty();
		property = mapper.readValue(Paths.get(requestURI).toFile(), ResponseListProperty.class);
		responses.addAll(property.getResponseList());
	}

	/**
	 * リクエストに使用するURIを返します。
	 * @return 使用するURI
	 */
	public String getRequestURI() {
		return requestURI;
	}



}
