package com.budwk.commons.utils;

import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

import java.awt.geom.Point2D;

/**
 * @author wizzer(wizzer.cn) on 2019/12/30
 */
public class CoordUtil {
    private static WKTReader reader;

    private static CoordinateReferenceSystem crsWGS84;
    private static CoordinateReferenceSystem crsCGCS2000;

    private static MathTransform wgs84toCgcs2000;
    private static MathTransform cgcs2000toWgs84;

    public static void main(String[] args) {
        CoordUtil coordUtil = new CoordUtil();
        long startTime = System.currentTimeMillis();
        Point2D.Double p = new Point2D.Double(499702.8739, 3468050.6928);
        System.out.printf("经度=%7.11f,维度=%7.11f\r\n", p.x, p.y);
        p = coordUtil.CGCS2000_to_WGS84(p.x, p.y);
        System.out.printf("经度=%7.11f,维度=%7.11f\r\n", p.x, p.y);
        p = coordUtil.WGS84_to_CGCS2000(p.x, p.y);
        System.out.printf("经度=%7.11f,维度=%7.11f\r\n", p.x, p.y);
        long endTime = System.currentTimeMillis();
        System.out.println("耗时:" + (endTime - startTime) + "ms");
    }

    /**
     * 初始化坐标系信息，只需调用一次
     */
    public static void init() {
        try {
            reader = new WKTReader();
            //String wktWgs1984="GEOGCS[\"WGS 84\", DATUM[\"World Geodetic System 1984\", SPHEROID[\"WGS 84\", 6378137.0, 298.257223563, AUTHORITY[\"EPSG\",\"7030\"]], AUTHORITY[\"EPSG\",\"6326\"]], PRIMEM[\"Greenwich\", 0.0, AUTHORITY[\"EPSG\",\"8901\"]], UNIT[\"degree\", 0.017453292519943295], AXIS[\"Geodetic longitude\", EAST], AXIS[\"Geodetic latitude\", NORTH], AUTHORITY[\"EPSG\",\"4326\"]]";
            String wktCgcs2000 = "PROJCS[\"CGCS2000 / Gauss-Kruger CM 105E\",GEOGCS[\"China Geodetic Coordinate System 2000\",DATUM[\"China_2000\",SPHEROID[\"CGCS2000\",6378137,298.257222101,AUTHORITY[\"EPSG\",\"1024\"]],AUTHORITY[\"EPSG\",\"1043\"]],PRIMEM[\"Greenwich\",0,AUTHORITY[\"EPSG\",\"8901\"]],UNIT[\"degree\",0.0174532925199433,AUTHORITY[\"EPSG\",\"9122\"]],AUTHORITY[\"EPSG\",\"4490\"]],UNIT[\"metre\",1,AUTHORITY[\"EPSG\",\"9001\"]],PROJECTION[\"Transverse_Mercator\"],PARAMETER[\"latitude_of_origin\",0],PARAMETER[\"central_meridian\",105],PARAMETER[\"scale_factor\",1],PARAMETER[\"false_easting\",500000],PARAMETER[\"false_northing\",0],AUTHORITY[\"EPSG\",\"4507\"],AXIS[\"X\",NORTH],AXIS[\"Y\",EAST]]";
            crsCGCS2000 = CRS.parseWKT(wktCgcs2000);
            crsWGS84 = DefaultGeographicCRS.WGS84;
            wgs84toCgcs2000 = CRS.findMathTransform(crsWGS84, crsCGCS2000, true);
            cgcs2000toWgs84 = CRS.findMathTransform(crsCGCS2000, crsWGS84, true);

        } catch (FactoryException e) {
            e.printStackTrace();
        }
    }

    public CoordUtil() {
        if (reader == null) {
            init();
        }
    }

    private Point2D.Double transform(double x, double y, MathTransform mt) {
        try {
            Point point = (Point) reader.read("POINT (" + x + " " + y + ")");
            Point pGeo = (Point) JTS.transform(point, mt);
            return new java.awt.Point.Double(pGeo.getX(), pGeo.getY());
        } catch (ParseException | MismatchedDimensionException | TransformException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * CGCS2000_to_WGS84
     * @param x 经度
     * @param y 纬度
     * @return
     */
    public Point2D.Double CGCS2000_to_WGS84(double x, double y) {
        return transform(y, x, cgcs2000toWgs84);
    }

    /**
     * WGS84_to_CGCS2000
     * @param x 经度
     * @param y 纬度
     * @return
     */
    public Point2D.Double WGS84_to_CGCS2000(double x, double y) {
        Point2D.Double point = transform(x, y, wgs84toCgcs2000);
        point.setLocation(point.y, point.x);
        return point;
    }
}
