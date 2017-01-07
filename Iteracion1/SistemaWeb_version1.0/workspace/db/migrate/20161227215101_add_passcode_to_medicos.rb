class AddPasscodeToMedicos < ActiveRecord::Migration
  def change
    add_column :medicos, :passcode, :string
  end
end
