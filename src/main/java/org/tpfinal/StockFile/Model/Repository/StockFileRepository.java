package org.tpfinal.StockFile.Model.Repository;

import org.tpfinal.Interfaces.IntRepository;
import org.tpfinal.StockFile.Model.Entity.StockFile;

import java.util.ArrayList;
import java.util.List;

public class StockFileRepository implements IntRepository<StockFile> {
    private final List<StockFile> stockFileList;

    public StockFileRepository() {
        this.stockFileList = new ArrayList<>();
    }

    @Override
    public void add(StockFile add) {
        stockFileList.add(add);
    }

    @Override
    public StockFile search(String searchField) {
        for(StockFile stockFile : stockFileList){
            if(stockFile.getActivity().equals(searchField)){
                return stockFile;
            }
        }
        return null;
    }

    @Override
    public void update(StockFile toUpdate, StockFile updated) {
        toUpdate.setActivity(updated.getActivity());
        toUpdate.setDate(updated.getDate());
        toUpdate.setSale(updated.getSale());
        toUpdate.setPurchase(updated.getPurchase());
    }

    @Override
    public void remove(StockFile remove) {
        stockFileList.remove(remove);
    }
}
