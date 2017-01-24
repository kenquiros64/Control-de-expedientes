class Medico < ActiveRecord::Base
  rolify
  # Include default devise modules. Others available are:
  # :confirmable, :lockable, :timeoutable and :omniauthable
  devise :database_authenticatable, :registerable,
         :recoverable, :rememberable, :trackable, :validatable
         
    #validates_format_of :cedula, :with => /([1-9])-([0-9]){4}-([0-9]){4}/, :on => [ :create, :update ],  :message => "Ingresar de manera correcta"
    validates_presence_of :cedula , :on => [ :create, :update ],:message => "no puede estar vacío"
    validates_presence_of :nombre ,:on => [ :create, :update ], :message => "no puede estar vacío"
    validates_presence_of :apellido1 , :on => [ :create, :update ],:message => "no puede estar vacío"
    validates_presence_of :apellido2 , :on => [ :create, :update ],:message => "no puede estar vacío"
    validates_presence_of :nacionalidad,:on => [ :create, :update ], :message => "no puede estar vacío"
    validates_uniqueness_of :cedula, :on => [ :create, :update ],:message => "ya fue ingresada"
    validates_uniqueness_of :codigo, :on => [ :create, :update ],:message => "ya fue ingresada"
         
  def admin?
    self.roles.include?(:admin)
  end
  
   has_many :citum
   has_many :observacions
   has_and_belongs_to_many :pacientes, dependent: :destroy, :join_table => :medicos_pacientes
end
