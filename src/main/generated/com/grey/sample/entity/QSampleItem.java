package com.grey.sample.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSampleItem is a Querydsl query type for SampleItem
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSampleItem extends EntityPathBase<SampleItem> {

    private static final long serialVersionUID = 855805990L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSampleItem sampleItem = new QSampleItem("sampleItem");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath itemName = createString("itemName");

    public final QSample sample;

    //inherited
    public final DateTimePath<java.util.Date> updatedAt = _super.updatedAt;

    public QSampleItem(String variable) {
        this(SampleItem.class, forVariable(variable), INITS);
    }

    public QSampleItem(Path<? extends SampleItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSampleItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSampleItem(PathMetadata metadata, PathInits inits) {
        this(SampleItem.class, metadata, inits);
    }

    public QSampleItem(Class<? extends SampleItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.sample = inits.isInitialized("sample") ? new QSample(forProperty("sample")) : null;
    }

}

