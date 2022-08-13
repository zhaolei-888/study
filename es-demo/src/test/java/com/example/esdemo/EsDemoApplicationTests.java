package com.example.esdemo;

import com.alibaba.fastjson.JSON;
import com.example.esdemo.pojo.User;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Conditional;

import javax.lang.model.element.VariableElement;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * ES高级客户端 API 测试
 */
@SpringBootTest
class EsDemoApplicationTests {

	@Autowired
	@Qualifier("restHighLevelClient")
	private RestHighLevelClient client;

	@Test
	void contextLoads() {
	}

	//测试索引的创建  request  PUT kuang_index
	@Test
	void testCreateIndex() throws IOException {
		//1、创建索引请求
		CreateIndexRequest request = new CreateIndexRequest("kuang_index");
		//2、客户端执行请求 IndicesClient
		CreateIndexResponse indexResponse = client.indices().create(request, RequestOptions.DEFAULT);
		System.out.println(indexResponse);
	}

	//测试获取索引,判断索引是否存在
	@Test
	void testExistsIndex() throws IOException {
		//1、获取索引请求
		GetIndexRequest request = new  GetIndexRequest("kuang_index");
		//2、执行获取请求
		boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
		System.out.println(exists);
	}

	//测试删除索引
	@Test
	void testDeleteIndex() throws IOException {
		//1、删除索引请求
		DeleteIndexRequest request = new DeleteIndexRequest("kuang_index");
		//2、执行删除索引请求
		AcknowledgedResponse delete = client.indices().delete(request, RequestOptions.DEFAULT);
		//是否删除成功
		System.out.println(delete.isAcknowledged());
	}


	//测试添加文档
	@Test
	void testAddDocument() throws IOException {
		//创建对象
		User user = new User("张三", 3);
		//创建连接索引请求
		IndexRequest request = new IndexRequest("kuang_index");
		//设置文档规则  PUT kuang_index/_doc/1
		request.id("1");
		//将文档数据放入请求中
		request.source(JSON.toJSONString(user), XContentType.JSON);
        //客户端发送请求
		IndexResponse response = client.index(request, RequestOptions.DEFAULT);
		System.out.println(response);
		System.out.println(response.status());
	}

	//判断文档是否存在, 存在才去拿取数据
	@Test
	void testDocumentExists() throws IOException {
		GetRequest getRequest = new GetRequest("kuang_shen", "1");
		//不获取_source的上下文
		getRequest.fetchSourceContext(new FetchSourceContext(false));
		//排序字段
		getRequest.storedFields("_none_");
		boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);
		System.out.println(exists);
	}

	//获取文档数据
	@Test
	void testGetDocument() throws IOException {
		//创建获取文档请求
		GetRequest getRequest = new GetRequest("kuang_shen", "1");
		//客户端发送获取请求
		GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);
		System.out.println(response.getSource());
		System.out.println(response);
	}

	//更新文档数据
	@Test
	void testUpdateDocument() throws IOException {
		//创建更新文档请求
		UpdateRequest request = new UpdateRequest("kuang_shen", "1");
		//超时时间设置
		request.timeout("1s");
		//修改体设置  放入"doc"中
		User user = new User("法外狂徒张三", 23);
		request.doc(JSON.toJSONString(user), XContentType.JSON);
		//客户端发送更新操作
		UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
		System.out.println(response);
		System.out.println(response.status());
	}

	//删除文档
	@Test
	void deleteDocument() throws IOException {
		DeleteRequest request = new DeleteRequest("kuang_shen", "1");
		//超时时间设置
		request.timeout("1s");
		//客户端发送超时请求
		DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
		System.out.println(response);
		System.out.println(response.status());
	}

	//批量操作文档(批量添加、更新、删除)
	@Test
	void testBulkRequest() throws IOException {
		//批量操作请求
		BulkRequest bulkRequest = new BulkRequest();
		//设置超时时间
		bulkRequest.timeout("10s");
		List<User> users = Lists.newArrayList();
		users.add(new User("zhansan1", 12));
		users.add(new User("zhansan2", 16));
		users.add(new User("zhansan3", 18));
		users.add(new User("lisi1", 12));
		users.add(new User("lisi2", 16));
		users.add(new User("lisi3", 18));
		for (int i = 0; i < users.size(); i++) {
			/**
			 * 批量操作其实本质就是这里不同，
			 * 批量创建：new IndexRequest("kuang_shen");
			 * 批量更新：new UpdateRequest("kuang_shen", (i + 1) + "");
			 * 批量删除：new DeleteRequest("kuang_shen", (i + 1) + "");
			 * 批量获取：new GetRequest("kuang_shen", (i + 1) + "")
			 */
			IndexRequest indexRequest = new IndexRequest("kuang_shen");
			indexRequest.id((i +1) + "");
			indexRequest.source(JSON.toJSONString(users.get(i)), XContentType.JSON);
			bulkRequest.add(indexRequest);
		}
		//客户端发送请求
		BulkResponse response = client.bulk(bulkRequest, RequestOptions.DEFAULT);
		//response.hasFailures() 是否失败， false 为执行操作成功
		System.out.println(response.hasFailures());
	}


	//查询搜索
	@Test
	void testSearchRequest() throws IOException {
		//1、创建搜索请求
		SearchRequest searchRequest = new SearchRequest("kuang_shen");
		//2、构建搜索条件(搜索体)
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

		/**
		 * 3、创建查询条件，我们可以使用QueryBuilders工具来实现
		 * QueryBuilders.termQuery(); QueryBuilders.matchQuery(); 等等
		 */
		TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "lisi");

		/**
		 * sourceBuilder.query(); 设置查询条件
		 * sourceBuilder.highlighter(); 查询结果高亮
		 * sourceBuilder.from(0);  分页设置
		 * sourceBuilder.size(10); 分页设置
		 * 等等...
		 */
		sourceBuilder.query(termQueryBuilder);
		sourceBuilder.from(0);
		sourceBuilder.size(10);


		//搜索超时时间设置
		sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

		//4、将搜索体放入请求
		searchRequest.source(sourceBuilder);
		//4、客户端发送请求
		SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
		System.out.println(JSON.toJSONString(response.getHits()));		//接收响应数据
		System.out.println("=================打印文档数据====================");
		for (SearchHit documentFields : response.getHits().getHits()) {
			//将文档数据转换为map打印
			System.out.println(documentFields.getSourceAsMap());
		}
	}

}
