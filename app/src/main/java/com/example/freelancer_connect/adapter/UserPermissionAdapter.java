package com.example.freelancer_connect.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.freelancer_connect.R;
import com.example.freelancer_connect.model.UserPermission;

import java.util.List;

public class UserPermissionAdapter extends BaseAdapter {
    private Context context;
    private List<UserPermission> userList;

    public UserPermissionAdapter(Context context, List<UserPermission> userList) {
        this.context = context;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_user_permission, parent, false);
            holder = new ViewHolder();

            holder.tvName = convertView.findViewById(R.id.tv_name);
            holder.tvRole = convertView.findViewById(R.id.tv_role);
            holder.chkAccount = convertView.findViewById(R.id.chk_account);
            holder.chkNotification = convertView.findViewById(R.id.chk_notification);
            holder.chkAuthenticate = convertView.findViewById(R.id.chk_authenticate);
            holder.chkStatistic = convertView.findViewById(R.id.chk_statistic);
            holder.chkService = convertView.findViewById(R.id.chk_service);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        UserPermission user = userList.get(position);

        holder.tvName.setText(user.getName());
        holder.tvRole.setText(user.getRole());
        holder.chkAccount.setChecked(user.isCanManageAccount());
        holder.chkNotification.setChecked(user.isCanManageNotification());
        holder.chkAuthenticate.setChecked(user.isCanAuthenticate());
        holder.chkStatistic.setChecked(user.isCanStatistic());
        holder.chkService.setChecked(user.isCanManageService());

        return convertView;
    }

    static class ViewHolder {
        TextView tvName, tvRole;
        CheckBox chkAccount, chkNotification, chkAuthenticate, chkStatistic, chkService;
    }
}
