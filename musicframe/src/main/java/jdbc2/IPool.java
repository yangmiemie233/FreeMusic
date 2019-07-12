package jdbc2;

import java.sql.Connection;

public interface IPool {

	PoolConnection getConnection();

	Connection getConnectionNoPool();

}
