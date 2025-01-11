package com.javaweb.ConvertTo;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.utils.MapUtil;
// xet du lieu cho builder
@Component
public class BuildingSearchBuilderConverter {
    public BuildingSearchBuilder toBuildingSearchBuilder(Map<String, Object> params, List<String> typecode) {
    	BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
        																	.setName(MapUtil.getObject(params, "name", String.class))
        																	.setFloorarea(MapUtil.getObject(params, "floorarea", Long.class))
        																	.setWard(MapUtil.getObject(params, "ward", String.class))
        																	.setStreet(MapUtil.getObject(params, "street", String.class))
        																	.setDistrictid(MapUtil.getObject(params, "districtid", Long.class))
        																	.setNumberofbasement(MapUtil.getObject(params, "numberofbasement", Long.class))
        																	.setTypecode(typecode)
        																	.setManagername(MapUtil.getObject(params, "managername", String.class))
        																	.setManagerphonenumber(MapUtil.getObject(params, "managerphonenumber", String.class))
        																	.setRentpriceto(MapUtil.getObject(params, "rentpriceto", Long.class))
        																	.setRentpricefrom(MapUtil.getObject(params, "rentpricefrom", Long.class))
        																	.setAreafrom(MapUtil.getObject(params, "areafrom", Long.class))
        																	.setAreato(MapUtil.getObject(params, "areato", Long.class))
        																	.setStaffid(MapUtil.getObject(params, "staffid", Long.class))
        																	.build();

        return buildingSearchBuilder;
    }
}

