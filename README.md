# 雷神Plugin

雷神Pluginはマインクラフト内の雷を操るPluginです。  
雷神Pluginを利用することで今までより快適なサーバーライフを送ることができます。

<br>
 
## 雷神Pluginが提供する体験

<br>
<br>

指定したプレイヤーを**確実に殺す**こと

<br>
<br>

指定したプレイヤーをキルするだけならkillコマンドで十分ですが、**killコマンドは特定のプレイヤーを確実に殺すには不十分**です。
そこで指定したプレイヤーを**確実に殺す**ことを重視したPluginを作成しました。

ただ殺すだけでは面白みがないので、雷でカッコよく殺せるようにしました。 

<br>

## 導入手順

jarファイルをダウンロードし、spigotサーバーの`plugins`ディレクトリに設置してください。  

### ダウンロード

jarファイルは[GitHub Package Registry](https://github.com/kuroko3417?tab=packages)からダウンロード、  
または`curl`、`wget`コマンドを利用してダウンロードしてください。

#### curl

Raigin-1.15.1_v1.0.0.jar
```
curl -L https://github.com/kuroko3417/mc-plugin-raijin/raw/master/packagefiles/Raijin-1.15.1_v1.0.0.jar -o Raigin-1.15.1_v.1.0.0.jar
```

Raigin-1.15.2_v1.0.0.jar
```
curl -L https://github.com/kuroko3417/mc-plugin-raijin/raw/master/packagefiles/Raijin-1.15.2_v1.0.0.jar -o Raigin-1.15.2_v.1.0.0.jar
```

Raigin-1.16.1_v1.0.0.jar
```
curl -L https://github.com/kuroko3417/mc-plugin-raijin/raw/master/packagefiles/Raijin-1.16.1_v1.0.0.jar -o Raigin-1.16.1_v.1.0.0.jar
```

Raigin-1.16.2_v1.0.0.jar
```
curl -L https://github.com/kuroko3417/mc-plugin-raijin/raw/master/packagefiles/Raijin-1.16.2_v1.0.0.jar -o Raigin-1.16.2_v.1.0.0.jar
```

Raigin-1.16.3_v1.0.0.jar
```
curl -L https://github.com/kuroko3417/mc-plugin-raijin/raw/master/packagefiles/Raijin-1.16.3_v1.0.0.jar -o Raigin-1.16.3_v.1.0.0.jar
```

#### wget


Raigin-1.15.1_v1.0.0.jar
```
wget -L https://github.com/kuroko3417/mc-plugin-raijin/raw/master/packagefiles/Raijin-1.15.1_v1.0.0.jar
```

Raigin-1.15.2_v1.0.0.jar
```
wget -L https://github.com/kuroko3417/mc-plugin-raijin/raw/master/packagefiles/Raijin-1.15.2_v1.0.0.jar
```

Raigin-1.16.1_v1.0.0.jar
```
wget -L https://github.com/kuroko3417/mc-plugin-raijin/raw/master/packagefiles/Raijin-1.16.1_v1.0.0.jar
```

Raigin-1.16.2_v1.0.0.jar
```
wget -L https://github.com/kuroko3417/mc-plugin-raijin/raw/master/packagefiles/Raijin-1.16.2_v1.0.0.jar
```

Raigin-1.16.3_v1.0.0.jar
```
wget -L https://github.com/kuroko3417/mc-plugin-raijin/raw/master/packagefiles/Raijin-1.16.3_v1.0.0.jar
```


## 主な使い方

雷神Pluginはコマンド経由で操作を行います。主な使い方は以下の通りです。

 - 指定したプレイヤーに雷を落とす。
 - 指定したプレイヤーグループに雷を落とす。
 - 指定したプレイヤーにスポーンするたびに雷を落とす。
 - 指定したプレイヤーがJoinしたときに雷を落とす。

雷を受けたプレイヤーは即死します。（コマンド経由で設定変更可能）

<br>

### 任意のタイミングで指定したプレイヤーに雷を落とす。

下記のコマンドを入力し、`<playerName>`の部分に雷を落としたいプレイヤーを指定します。

<br>

`/ri rs <playerName>`

<br>

複数指定する場合は半角スペース区切りでプレイヤー名を指定します。

<br>

### 指定したプレイヤーグループに雷を落とす


下記のコマンドを入力し、`<groupName>`の部分に雷を落としたいプレイヤーグループを指定します。

<br>

`/ri rs <groupName>`

<br>

コマンド実行後、指定したプレイヤーグループに所属するプレイヤー全員に雷を落とします。
プレイヤーグループとはプレイヤーをまとめたグループのことで、雷神Pluginの機能の一つです。

#### プレイヤーグループの設定方法

下記のコマンドを入力し、`<groupName>`の部分にプレイヤーのグループ名を指定します。  
その後は`<playerNames>`の部分にプレイヤーグループに所属させるプレイヤー名を指定します。

<br>

`/ri target <groupName> <playerNames>`

<br>

プレイヤーグループを設定することでグループ単位に雷を落とすことが可能になるので、
複数人のトロールが発生しても速やかに対処することが可能です。

この機能を有効活用することで、快適なサーバーライフを送ることができると思います。


### 指定したプレイヤーにスポーンするたびに雷を落とす

下記のコマンドを入力し、`<playerName>`の部分にスポーン時に雷を落としたいプレイヤーを指定します。

<br>

`/ri pur <playerName>`

注意:複数人同時指定はできません。

<br>

設定後、指定したプレイヤーがスポーンすると、スポーンしたプレイヤーの無敵時間が終了したタイミングで雷が落ちます。
雷は即死効果があるので、このコマンドを用いて人の手を介さずに**確実にリスキル**することが可能です。

元ネタはTETU_tanukiさんの煉獄です。

<br>

### 指定したプレイヤーがJoinしたときに雷を落とす

下記のコマンドを入力し、`<playerName>`の部分にスポーン時に雷を落としたいプレイヤーを指定します。

<br>

`/ri obs <playerName>`

注意:複数人同時指定はできません。

<br>

設定後、指定したプレイヤーがJoinすると、Joinしたプレイヤーの無敵時間が終了したタイミングで雷が落ちます。
このコマンドを利用することでleft the gameしてkillコマンドによるキルから免れたプレイヤーを**確実に殺す**事ができます

※最近の配信にてKUNさんがkillコマンドでINKYATENSEIさんをキルすると同時にleft the gameで上手く逃げた所をみて思いつきました。

<br>

## 詳細な仕様

### コマンド入力時に補完表示されるプレイヤー一覧について

補完表示されるプレイヤーの一覧はホワイトリストを元に生成しています。（`ri rs`コマンド以外）
そのためオフラインプレイヤーの設定も可能です。

`ri rs`コマンドは任意のタイミングで雷を落とすコマンドなので、補完表示はオンラインプレイヤーのみの仕様です。

### ri obsコマンドの設定について

`ri obs`コマンドにて設定したプレイヤーの処理が実行されると、設定が自動で解除されます。（1回きり）



## コマンドリファレンス

基本構文

`ri <Options...>`

## 主要コマンド



### ri pur

***

スポーン時に雷を落としたいプレイヤーを設定するオプションです。  
TETU_tanukiさんのリスキル（煉獄）が元ネタです。

|Command|Options|Desecaption|  
|---|---|---|
|ri pur add|&lt;groupName&gt;|対象のプレイヤーを追加します。|
|ri pur remove|&lt;groupName&gt;|対象からプレイヤを除外します。|
|ri pur list|none|対象のプレイヤー名一覧を表示します。|

備考:オフラインプレイヤーを指定することが可能です。

### ri obs

***

Join時に雷を落としたいプレイヤーを設定するコマンドです。  
killコマンド実行するタイミングで上手く逃げたINKYATENSEIを確殺するために作成しました。

|Command|Options|Desecaption|  
|---|---|---|
|ri obs add|&lt;groupName&gt;|対象のプレイヤーを追加します。|
|ri obs remove|&lt;groupName&gt;|対象からプレイヤを除外します。|
|ri obs list|none|対象のプレイヤー名一覧を表示します。|

備考:オフラインプレイヤーを指定することが可能です。

注意:設定対象がJoinして雷が落ちたあと、設定対象から自動的に除外されます。

### ri rs

***

指定したプレイヤーまたはプレイヤーグループに雷を落とします。

|Command|Options|Desecaption|  
|---|---|---|
|ri rs|&lt;playerNames... or groupNames...&gt;|指定したプレイヤーまたはプレイヤーグループに雷を落とします。|  

## 設定コマンド

### ri death

***

雷を落としたときに確殺するかどうか設定します。

|Command|Options|Desecaption|  
|---|---|---|
|ri death true|none|雷を落としたときに確殺します。|  
|ri death false|none|雷を落としたときに確殺しません。|  
|ri death reset|none|設定を初期化します。（初期値:true）|

### ri power

***

雷の落雷地点の爆発威力を設定します。

|Command|Options|Desecaption|  
|---|---|---|
|ri power|0 ~ 1000|爆発する威力を設定します。|
|ri power reset|none|設定を初期化します。（初期値:0）|

### ri target

***

プレイヤーリストを設定するコマンドです。

|Command|Options|Desecaption|  
|---|---|---|
|ri target add|&lt;groupName&gt; &lt;playerNames...&gt;|プレイヤーをリストに追加します。|
|ri target remove|&lt;groupName&gt; &lt;playerNames...&gt;|プレイヤーをリストから除外します。|
|ri target list|&lt;groupName&gt;|リストに所属するプレイヤー一覧を表示します。

### ri targets 

***

プレイヤーリストの設定を制御するコマンドです。

|Command|Options|Desecaption|  
|---|---|---|
|ri targets delete|&lt;groupName&gt;|プレイヤーリストを削除します。|
|ri targets clear|&lt;groupName&gt;|プレイヤーリストをすべて削除します。|
|ri targets list|none|プレイヤーリスト名の一覧を表示します。|

