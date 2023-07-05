package com.gccloud.dataset.service.impl.label;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gccloud.dataset.dao.DatasetLabelDao;
import com.gccloud.dataset.entity.DatasetLabelEntity;
import com.gccloud.dataset.entity.LabelEntity;
import com.gccloud.dataset.service.IDatasetLabelService;
import com.gccloud.dataset.vo.DatasetLabelVO;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hongyang
 * @version 1.0
 * @date 2023/7/5 14:01
 */
@Service
public class DatasetLabelServiceImpl extends ServiceImpl<DatasetLabelDao, DatasetLabelEntity> implements IDatasetLabelService {

    @Override
    public List<DatasetLabelVO> getDatasetByLabelId(String labelId) {
        return getBaseMapper().getDatasetByLabelId(labelId);
    }

    @Override
    public List<LabelEntity> getLabelByDatasetId(String datasetId) {
        return getBaseMapper().getLabelByDatasetId(datasetId);
    }

    @Override
    public void delete(String datasetId, String labelId) {
        LambdaQueryWrapper<DatasetLabelEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DatasetLabelEntity::getDatasetId, datasetId);
        wrapper.eq(DatasetLabelEntity::getLabelId, labelId);
        this.remove(wrapper);
    }

    @Override
    public void deleteByDatasetId(String datasetId) {
        LambdaQueryWrapper<DatasetLabelEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DatasetLabelEntity::getDatasetId, datasetId);
        this.remove(wrapper);
    }

    @Override
    public void deleteByLabelId(String labelId) {
        LambdaQueryWrapper<DatasetLabelEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DatasetLabelEntity::getLabelId, labelId);
        this.remove(wrapper);
    }

    @Override
    public void addByDatasetId(String datasetId, List<String> labelIds) {
        if (StringUtils.isBlank(datasetId)) {
            return;
        }
        if (labelIds == null || labelIds.isEmpty()) {
            return;
        }
        List<DatasetLabelEntity> list = Lists.newArrayList();
        for (String labelId : labelIds) {
            DatasetLabelEntity entity = new DatasetLabelEntity();
            entity.setDatasetId(datasetId);
            entity.setLabelId(labelId);
            list.add(entity);
        }
        this.saveBatch(list);
    }

    @Override
    public void addByLabelId(String labelId, List<String> datasetIds) {
        if (StringUtils.isBlank(labelId)) {
            return;
        }
        if (datasetIds == null || datasetIds.isEmpty()) {
            return;
        }
        List<DatasetLabelEntity> list = Lists.newArrayList();
        for (String datasetId : datasetIds) {
            DatasetLabelEntity entity = new DatasetLabelEntity();
            entity.setDatasetId(datasetId);
            entity.setLabelId(labelId);
            list.add(entity);
        }
        this.saveBatch(list);
    }
}
