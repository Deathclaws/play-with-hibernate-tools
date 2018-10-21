package com.deathclaws.hibernate;

import org.hibernate.cfg.reveng.DelegatingReverseEngineeringStrategy;
import org.hibernate.cfg.reveng.ReverseEngineeringStrategy;
import org.hibernate.cfg.reveng.TableIdentifier;

public class CustomStrategy extends DelegatingReverseEngineeringStrategy {

	public CustomStrategy(ReverseEngineeringStrategy delegate) {
		super(delegate);
	}

	@Override
	public String tableToClassName(TableIdentifier tableIdentifier) {
		String origineTableName = super.tableToClassName(tableIdentifier);
		if (origineTableName.startsWith("Ref")) {
			return origineTableName.substring(3);
		} else {
			return origineTableName;
		}
	}

}