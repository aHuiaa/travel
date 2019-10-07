package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService {
    FavoriteDao favoriteDao = new FavoriteDaoImpl();
    @Override
    public Favorite findFavorite(String rid, int uid) {
        Favorite favorite = favoriteDao.findFavorite(rid ,uid);
        return favorite;
    }

    @Override
    public void favorite(String rid, String uid) {
        favoriteDao.favorite(rid,uid);
    }

    @Override
    public void noFavorite(String rid, String uid) {
        favoriteDao.noFavorite(rid,uid);
    }
}
