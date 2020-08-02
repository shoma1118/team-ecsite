
package jp.co.internous.sky.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import jp.co.internous.sky.model.domain.dto.PurchaseHistoryDto;

@Mapper
public interface TblPurchaseHistoryMapper {
	//settlement
	boolean insert(@Param("userId") int userId, @Param("destinationId") int destinationId);
	
	//purchaseHistory
	List<PurchaseHistoryDto> findByUserId(@Param("userId") int userId);
	
	boolean deleteByUserId(@Param("userId") int userId);
	

}